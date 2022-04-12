package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.InvoiceService;
import com.etiya.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.DeleteInvoiceRequest;
import com.etiya.rentACar.business.requests.invoiceRequests.UpdateInvoiceRequest;
import com.etiya.rentACar.business.responses.invoiceResponses.ListInvoiceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class InvoiceController {

    private InvoiceService billService;

    public InvoiceController(InvoiceService billService) {
        this.billService = billService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateInvoiceRequest createBillRequest){
        return this.billService.add(createBillRequest);
    }
    @PutMapping("/update")
    public Result update(@RequestBody UpdateInvoiceRequest updateBillRequest){
        return this.billService.update(updateBillRequest);
    }
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteInvoiceRequest deleteBillRequest){
        return this.billService.delete(deleteBillRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<ListInvoiceDto>> getAll(){
        return this.billService.getAll();
    }

    @GetMapping("/findCreateDate")
    public DataResult<List<ListInvoiceDto>> findCreateDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate ){
        return this.billService.findByCreateDateBetween(startDate,endDate);
    }
    @GetMapping("/getAllCustomerId")
    public DataResult<List<ListInvoiceDto>> getByCustomerId(@RequestParam int customerId){
        return this.billService.getByCustomerId(customerId);
    }
}
