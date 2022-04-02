package com.etiya.rentACar.business.responses.additionalServiceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//Getter ve Setter oluşturur.
@AllArgsConstructor// Parametreli Constructor oluşturur.
@NoArgsConstructor//Parametresiz Constructor oluşturur.
public class AdditionalServiceDto {

    private int id;

    private String name;

    private int dailyPrice;
}
