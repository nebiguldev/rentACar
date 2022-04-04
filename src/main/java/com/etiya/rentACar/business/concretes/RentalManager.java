package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.OrderedAdditionalServiceService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStateRequest;
import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.CreateOrderedAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.ReturnRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.RentalDao;
import com.etiya.rentACar.entities.CarStates;
import com.etiya.rentACar.entities.Rental;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalManager implements RentalService {
    private RentalDao rentalDao;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private OrderedAdditionalServiceService orderedAdditionalServiceService;

    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService, OrderedAdditionalServiceService orderedAdditionalServiceService) {
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
    }
   // kiralama ekleme
    @Override
    public Result add(CreateRentalRequest createRentalRequest) {
        int carId = createRentalRequest.getCarId();
        checkIfCarState(carId);//aşağıda yazdığımız methodu çağırıyoruz.//aracın durumu müsaitise kiralanabilir.

        Rental result = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        this.rentalDao.save(result);


        int rentCityId = createRentalRequest.getRentCityId();
        CarStates states = CarStates.Rented;
        updateCarState(carId, rentCityId, states);

        int rentalId = result.getId();
        List<Integer> additionalServicesId = createRentalRequest.getAdditionalServiceId();
        createOrderedAdditionalService(rentalId, additionalServicesId);

        return new SuccessResult(BusinessMessages.RentalMessages.RENTAL_ADD);
    }
    //kiralama kaydı güncelleme
    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        Rental result = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        this.rentalDao.save(result);
        return new SuccessResult(BusinessMessages.RentalMessages.RENTAL_UPDATE);
    }
    //kiralama kaydı silme
    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        int rentalId = deleteRentalRequest.getId();
        this.rentalDao.deleteById(rentalId);
        return new SuccessResult(BusinessMessages.RentalMessages.RENTAL_DELETED);
    }

    //aracın teslimi .
    public Result returnRental(ReturnRentalRequest returnRentalRequest) {

        CarStates states = CarStates.Available;
        int carId = returnRentalRequest.getCarId();
        int returnCıtyId = returnRentalRequest.getReturnCityId();
        updateCarState(carId, returnCıtyId, states);

        return new SuccessResult(BusinessMessages.RentalMessages.RENTAL_RETURNED);
    }
    //kiralamayı listeliyor.getall
    @Override
    public DataResult<List<ListRentalDto>> getAll() {
        List<Rental> results = this.rentalDao.findAll();
        List<ListRentalDto> response = results.stream()
                .map(rental -> modelMapperService.forDto().map(rental, ListRentalDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListRentalDto>>(response);
    }

    //aracın durumunu kontrol ediyoruz
    private void checkIfCarState(int carId) {
        CarDto result = this.carService.getById(carId);
        if (result.getCarStateName() != CarStates.Available) {
            throw new BusinessException(BusinessMessages.RentalMessages.CAR_NOT_AVAILABLE);
        }

    }
    //teslim alınma söz konusu olamaz. böyle bir id yok
    private void checkIfRentalIdExists(int rentalId) {
        if (!this.rentalDao.existsById(rentalId)) {
            throw new BusinessException(BusinessMessages.RentalMessages.RENTAL_NOT_EXIST);
        }
    }
    //car state
    private void updateCarState(int carId, int cityId, CarStates states) {
        UpdateCarStateRequest updateCarStateRequest = new UpdateCarStateRequest();
        updateCarStateRequest.setCarId(carId);
        updateCarStateRequest.setCarStateName(states);
        updateCarStateRequest.setCityId(cityId);
        this.carService.updateCarState(updateCarStateRequest);
    }
    //additional servis ekleme fore each döndü.birden çok additional service eklenebilir.
    private void createOrderedAdditionalService(int rentalId, List<Integer> additionalServicesId) {
        CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest = new CreateOrderedAdditionalServiceRequest();
        for (int additionalServiceId : additionalServicesId) {
            createOrderedAdditionalServiceRequest.setRentalId(rentalId);
            createOrderedAdditionalServiceRequest.setAdditionalServiceId(additionalServiceId);
            this.orderedAdditionalServiceService.add(createOrderedAdditionalServiceRequest);
        }
    }


}
