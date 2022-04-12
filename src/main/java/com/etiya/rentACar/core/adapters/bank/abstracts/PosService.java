package com.etiya.rentACar.core.adapters.bank.abstracts;

import com.etiya.rentACar.entities.CreditCard;

public interface PosService {
    boolean makePayment(CreditCard creditCard);


}
