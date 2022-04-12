package com.etiya.rentACar.business.requests.rentalRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
    @JsonIgnore
    private int id;

    private LocalDate rentDate;

    private LocalDate returnDate;

    private double dailyPrice;

    private int customerId;

    private int carId;

    private int rentCityId;

    private int returnCityId;

    private List<Integer> additionalServiceId;



}
