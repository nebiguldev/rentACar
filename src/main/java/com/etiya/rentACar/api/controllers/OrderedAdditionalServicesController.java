package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.OrderedAdditionalServiceService;
import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.CreateOrderedAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.DeleteOrderedAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.orderedAdditionalServiceRequest.UpdateOrderedAdditionalServiceRequest;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderedAdditionalServices")
public class OrderedAdditionalServicesController {
    private OrderedAdditionalServiceService orderedAdditionalServiceService;

    public OrderedAdditionalServicesController(OrderedAdditionalServiceService orderedAdditionalServiceService) {
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest){
        return this.orderedAdditionalServiceService.add(createOrderedAdditionalServiceRequest);
    }

    @DeleteMapping("/delete")
    public  Result delete(@RequestBody DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest){
        return  this.orderedAdditionalServiceService.delete(deleteOrderedAdditionalServiceRequest);
    }

    @PutMapping ("/update")
    public Result update(@RequestBody UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest){
        return this.orderedAdditionalServiceService.update(updateOrderedAdditionalServiceRequest);
    }
}
