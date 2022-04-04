package com.etiya.rentACar.business.responses.rentalResponses;

import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.City;
import com.etiya.rentACar.entities.Customer;
import com.etiya.rentACar.entities.OrderedAdditionalService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {
 private int id;


 private LocalDate rentDate;


 private LocalDate returnDate;


 private int rentCityId;


 private int returnCityId;


 private double dailyPrice;


 private int carId;


 private int customerId;



private int orderedAdditionalServicesId;

}
