package com.etiya.rentACar.business.abstracts;


import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.CreateOrderedAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.DeleteOrderedAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.UpdateOrderedAdditionalServiceRequest;
import com.etiya.rentACar.core.utilities.results.Result;

public interface OrderedAdditionalServiceService {

    Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest);

    Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest);

    Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest);
}
