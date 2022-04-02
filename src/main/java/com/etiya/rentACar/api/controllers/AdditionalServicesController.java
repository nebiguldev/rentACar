package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

@RestController//İlgili sınıfın  Controller olma özelliği katar
@RequestMapping("/api/additionalServices")//Hangi adreste yayınlanması gerektiğini
public class AdditionalServicesController {
    private AdditionalServiceService additionalServiceService;

    public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateAdditionalServiceRequest createAdditionalServiceRequest) {
        return this.additionalServiceService.add(createAdditionalServiceRequest);
    }
    @PutMapping("/update")
   public Result update(@RequestBody UpdateAdditionalServiceRequest updateAdditionalServiceRequest){
        return this.additionalServiceService.update(updateAdditionalServiceRequest);
    }
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteAdditionalServiceRequest deleteAdditionalServiceRequest){
        return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
    }

}
