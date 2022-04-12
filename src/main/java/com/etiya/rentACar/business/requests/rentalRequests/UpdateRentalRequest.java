package com.etiya.rentACar.business.requests.rentalRequests;

import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

    private int id;

    private LocalDate rentDate;

    private LocalDate returnDate;

    private Customer customer;

    private int returnCity;

    private int rentCity;

    private Car car;

    private int afterRentKilometer;

    private int beforeRentKilometer;





}
