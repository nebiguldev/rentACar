package com.etiya.rentACar.business.responses.rentalResponses;


import com.etiya.rentACar.entities.OrderedAdditionalService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {
 @JsonIgnore
 private int id;

 private LocalDate rentDate;

 private LocalDate returnDate;

 private String customerFirstName;

 private String customerLastName;

 private int customerId;

 private double dailyPrice;

 private String carDescription;

 private int rentCityName;

 private int returnCityName;



 List<OrderedAdditionalService> orderedAdditionalServices;

}
