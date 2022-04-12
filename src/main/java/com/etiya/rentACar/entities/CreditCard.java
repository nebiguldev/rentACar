package com.etiya.rentACar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private  int id;

    @Column(name = "credit_card_no")
    @Size(min = 16,max = 16)
    private String creditCardNo;

    @Column(name = "expire_date")
    private String expireDate;

    @Column(name = "cvv")
    @Size(min = 3,max = 3)
    private String cvv;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;





}
