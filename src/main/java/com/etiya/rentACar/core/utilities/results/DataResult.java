package com.etiya.rentACar.core.utilities.results;

import com.etiya.rentACar.entities.Invoice;

import java.net.PortUnreachableException;

//date ile gönderdiğim result
public class DataResult<T> extends Result{
    private T data;

    public DataResult(T data, boolean success) {
        super(success);
        this.data=data;
    }

    public DataResult(T data ,boolean success, String message) {
        super(success, message);
        this.data =data;
    }

   public T getData(){
        return  data;
   }
}
