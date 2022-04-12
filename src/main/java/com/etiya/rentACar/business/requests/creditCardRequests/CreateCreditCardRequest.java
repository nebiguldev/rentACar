package com.etiya.rentACar.business.requests.creditCardRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {
    @JsonIgnore
    @Column(name = "id")

    private  int id;

    @Column(name = "credit_card_no")
    @Size(min = 16,max = 16)
    @NotNull
    private String creditCardNo;

    @Column(name = "expire_date")
    @NotNull
    private String expireDate;

    @Column(name = "cvv")
    @NotNull
    @Size(min = 3,max = 3)
    private String cvv;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;
}
