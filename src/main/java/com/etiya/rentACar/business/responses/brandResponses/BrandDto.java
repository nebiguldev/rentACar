package com.etiya.rentACar.business.responses.brandResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//Getter ve Setter oluşturur.
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
    private int id;
    private String name;
}
