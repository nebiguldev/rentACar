package com.etiya.rentACar.business.requests.paymentRequests;


import com.etiya.rentACar.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.CreateOrderedAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

    @JsonIgnore
    private int id;

    @NotNull
    private String creditCardFirstName;

    @NotNull
    private String creditCardLastName;

    @NotNull
    @Size(min=16,max=16)
    private String creditCardNo;

    @NotNull
    private String expirationDate;

    @NotNull
    @Size(min=3,max=3)
    private String cvv;

    private int customerId;

    private  CreateRentalRequest createRentalRequest;

    private List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequests;

    private CreateInvoiceRequest createInvoiceRequest;

    private CreateCreditCardRequest creditCard;

    private List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequest;



}

