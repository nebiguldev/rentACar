package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.OrderedAdditionalServiceService;
import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.CreateOrderedAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.DeleteOrderedAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.UpdateOrderedAdditionalServiceRequest;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.OrderedAdditionalServiceDao;
import com.etiya.rentACar.entities.OrderedAdditionalService;
import org.springframework.stereotype.Service;

@Service
public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService {

    private OrderedAdditionalServiceDao orderedAdditionalServiceDao;
    private ModelMapperService modelMapperService;

    public OrderedAdditionalServiceManager(OrderedAdditionalServiceDao orderedAdditionalServiceDao, ModelMapperService modelMapperService) {
        this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) {
        OrderedAdditionalService result = this.modelMapperService.forRequest()
                .map(createOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
        this.orderedAdditionalServiceDao.save(result);
        return new SuccessResult();
    }

    @Override//KONTROL ET
    public Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest) {
        int orderedAdditionalServiceId = deleteOrderedAdditionalServiceRequest.getId();
        this.orderedAdditionalServiceDao.deleteById(orderedAdditionalServiceId);
        return new SuccessResult("ORDERED_ADDITIONAL_DELETED");
    }

    @Override
    public Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) {
        OrderedAdditionalService result = this.modelMapperService.forRequest()
                .map(updateOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
        this.orderedAdditionalServiceDao.save(result);
        return new SuccessResult();
    }
}
