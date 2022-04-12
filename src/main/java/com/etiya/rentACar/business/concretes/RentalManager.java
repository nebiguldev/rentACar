package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.OrderedAdditionalServiceService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarCityRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStateRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateKilometerRequest;
import com.etiya.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.ReturnRentalRequest;
import com.etiya.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.business.responses.rentalResponses.ListRentalDto;
import com.etiya.rentACar.business.responses.rentalResponses.RentalDto;
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

    @Override
    public DataResult<Rental> add(CreateRentalRequest createRentalRequest) {


        checkIfCarState(createRentalRequest.getCarId());
        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);


        CarDto car = carService.getById(createRentalRequest.getCarId());

        rental.setBeforeRentalKilometer((int) car.getKilometerInfo());
        rentalDao.save(rental);


        updateCarCity(car.getId(), createRentalRequest.getReturnCityId());
        updateCarState(car.getId(), CarStates.Rented);

        return new SuccessDataResult<Rental>(rental, BusinessMessages.RentalMessages.RENTAL_ADD);
    }


    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        Rental result = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        this.rentalDao.save(result);
        return new SuccessResult(BusinessMessages.RentalMessages.RENTAL_UPDATE);
    }

    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        int rentalId = deleteRentalRequest.getId();
        this.rentalDao.deleteById(rentalId);
        return new SuccessResult(BusinessMessages.RentalMessages.RENTAL_DELETED);
    }

    @Override
    public Result returnRental(ReturnRentalRequest returnRentalRequest) {
        //km ve tarih update
        Rental result = this.rentalDao.getById(returnRentalRequest.getId());
        result.setReturnDate(returnRentalRequest.getReturnDate());
        result.setAfterRentalKilometer((int) returnRentalRequest.getAfterRentKilometer());//int koyduruyor?
        this.rentalDao.save(result);


        int carId = returnRentalRequest.getCarId();
        //km update
        updateCarKilometer(carId, returnRentalRequest.getAfterRentKilometer());
        //city update
        updateCarCity(carId, returnRentalRequest.getReturnCityId());
        //status update
        updateCarState(carId, CarStates.Available);

        return new SuccessResult(BusinessMessages.RentalMessages.RENTAL_RETURNED);
    }

    @Override
    public RentalDto getById(int rentalId) {
        Rental result = this.rentalDao.getById(rentalId);
        RentalDto rentalDto = this.modelMapperService.forDto().map(result, RentalDto.class);
        return rentalDto;
    }

    //aracın km sini güncelleme
    private void updateCarKilometer(int carId, double carKilometer) {

        UpdateKilometerRequest updateKilometerRequest = new UpdateKilometerRequest();
        updateKilometerRequest.setCarId(carId);
        updateKilometerRequest.setKilometerInfo(carKilometer);
        this.carService.updateCarKilometer(updateKilometerRequest);
    }

    //kiralanan araçların listelenmesi getall
    @Override
    public DataResult<List<ListRentalDto>> getAll() {
        List<Rental> results = this.rentalDao.findAll();
        List<ListRentalDto> response = results.stream().map(rental -> this.modelMapperService.forDto()
                .map(rental, ListRentalDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListRentalDto>>(response);
    }


    private void checkIfCarState(int carId) {
        CarDto result = this.carService.getById(carId);
        if (result.getCarStateName() != CarStates.Available) {
            throw new BusinessException(BusinessMessages.RentalMessages.CAR_NOT_AVAILABLE);
        }

    }


    //araç durumu güncelleme
    private void updateCarState(int carId, CarStates status) {
        UpdateCarStateRequest updateCarStateRequest = new UpdateCarStateRequest();
        updateCarStateRequest.setId(carId);
        updateCarStateRequest.setCarStateName(status);
        this.carService.updateCarState(updateCarStateRequest);

    }


    //aracın güncel şehrini güncelleme
    private void updateCarCity(int carId, int cityId) {
        UpdateCarCityRequest updateCarCityRequest = new UpdateCarCityRequest();
        updateCarCityRequest.setCityId(carId);
        updateCarCityRequest.setCityId(cityId);
        this.carService.updateCarCity(updateCarCityRequest);
    }


}