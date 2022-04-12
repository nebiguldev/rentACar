package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.*;

import com.etiya.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.CreateOrderedAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.rentACar.business.responses.paymentResponses.ListPaymentDto;
import com.etiya.rentACar.core.adapters.bank.abstracts.PosService;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.PaymentDao;
import com.etiya.rentACar.entities.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service

public class PaymentManager implements PaymentService {
    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private OrderedAdditionalServiceService orderedAdditionalServiceService;
    private InvoiceService invoiceService;
    private AdditionalServiceService additionalServiceService;
    private RentalService rentalService;
    private CustomerService customerService;
    private PosService posService;


    public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService,
                          RentalService rentalService, OrderedAdditionalServiceService orderedAdditionalServiceService,
                          InvoiceService invoiceService, AdditionalServiceService additionalServiceService,
                          CustomerService customerService, PosService posService) {
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.rentalService = rentalService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
        this.invoiceService = invoiceService;
        this.additionalServiceService = additionalServiceService;
        this.posService = posService;
        this.customerService = customerService;

    }

    @Override
    @Transactional
    public Result add(CreatePaymentRequest createPaymentRequest) {

        this.checkIfCreditCard(createPaymentRequest);

        Payment result = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

        //kiralama
        Rental rental = this.rentalService.add(createPaymentRequest.getCreateRentalRequest()).getData();
        //Ordered Service
        this.addAdditionalServiceOrder(createPaymentRequest, rental.getId());
        //TotalPrice
        calculateTotalPrice(createPaymentRequest);
        //Customer Service
        Customer customer = (Customer) this.customerService.getById(createPaymentRequest.getCustomerId()).getData();
        //fatura ekleme
        Invoice invoice = this.invoiceAddService(createPaymentRequest, rental.getId(), customer.getId());

        //ödeme ekleme
        result.setTotalPrice(calculateTotalPrice(createPaymentRequest));
        result.setInvoice(invoice);
        result.setCustomer(customer);
        result.setRental(rental);
        this.paymentDao.save(result);

        return new SuccessResult();
    }

    @Override
    //ödemelerin listelenmesi
    public DataResult<List<ListPaymentDto>> getAll() {
        List<Payment> result = paymentDao.findAll();
        List<ListPaymentDto> response = result.stream()
                .map(payment -> modelMapperService.forDto().map(payment, ListPaymentDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListPaymentDto>>(response);
    }

    private double calculateTotalPrice(CreatePaymentRequest createPaymentRequest) {

        //different location +750 tl ve total price a ekleme
        List<Integer> additionalServiceId = createPaymentRequest.getCreateOrderedAdditionalServiceRequest()
                .stream().map(CreateOrderedAdditionalServiceRequest::getAdditionalServiceId).collect(Collectors.toList());
        double totalPrice = 0;
        Period day = Period.between(createPaymentRequest.getCreateRentalRequest().getRentDate(), createPaymentRequest.getCreateRentalRequest().getReturnDate());
        int daysCount = day.getDays();
        if (!Objects.equals(createPaymentRequest.getCreateRentalRequest().getReturnCityId(), createPaymentRequest.getCreateRentalRequest().getRentCityId())) {
            totalPrice += 750;
        }

        totalPrice += createPaymentRequest.getCreateRentalRequest().getDailyPrice() * daysCount;

        for (Integer additionalservice : additionalServiceId) {
            totalPrice += this.additionalServiceService.getById(additionalservice).getData().getDailyPrice() * daysCount;
        }

        return totalPrice;

    }

    private void checkIfCreditCard(CreatePaymentRequest createPaymentRequest) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNo(createPaymentRequest.getCreditCard().getCreditCardNo());
        creditCard.setCvv(createPaymentRequest.getCreditCard().getCvv());
        creditCard.setExpireDate(createPaymentRequest.getCreditCard().getExpireDate());
        if (!this.posService.makePayment(creditCard)) {
            throw new BusinessException("Bu kart geçersiz.");
        }
    }


    private Invoice invoiceAddService(CreatePaymentRequest createPaymentRequest, int rentalId, int customerId) {
        CreateInvoiceRequest createInvoiceRequest = createPaymentRequest.getCreateInvoiceRequest();
        createInvoiceRequest.setRentalId(rentalId);
        createInvoiceRequest.setCustomerId(customerId);
        createInvoiceRequest.setTotalPrice(calculateTotalPrice(createPaymentRequest));
        return invoiceService.add(createInvoiceRequest).getData();
    }

    private void addAdditionalServiceOrder(CreatePaymentRequest createPaymentRequest, int rentalId) {

        for (CreateOrderedAdditionalServiceRequest additionalServiceOrderRequest :
                createPaymentRequest.getCreateOrderedAdditionalServiceRequests()) {
            additionalServiceOrderRequest.setRentalId(rentalId);
            orderedAdditionalServiceService.add(additionalServiceOrderRequest);
        }

    }

}
