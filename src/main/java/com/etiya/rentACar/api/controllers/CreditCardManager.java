package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.CreditCardService;
import com.etiya.rentACar.dataAccess.abstracts.CreditCardDao;
import org.springframework.stereotype.Service;

@Service
public class CreditCardManager implements CreditCardService {
    private CreditCardDao creditCardDao;
}
