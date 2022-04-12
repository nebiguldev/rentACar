package com.etiya.rentACar.core.adapters.bank.concrates;

import com.etiya.rentACar.core.adapters.bank.abstracts.PosService;
import com.etiya.rentACar.core.externalServices.HalkBankPosService;
import com.etiya.rentACar.entities.CreditCard;
import org.springframework.stereotype.Service;

@Service
public class HalkBankPosServiceAdapter implements PosService {
private HalkBankPosService halkBankPosService;

    public HalkBankPosServiceAdapter(HalkBankPosService halkBankPosService) {
        this.halkBankPosService = halkBankPosService;
    }

    @Override
    public boolean makePayment(CreditCard creditCard) {
        return halkBankPosService.makePayment(creditCard.getCreditCardNo(),creditCard.getExpireDate(),creditCard.getCvv());
    }
}
