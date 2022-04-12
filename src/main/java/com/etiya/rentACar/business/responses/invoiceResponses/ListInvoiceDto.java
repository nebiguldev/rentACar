package com.etiya.rentACar.business.responses.invoiceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListInvoiceDto {
    private int id;

    private String invoiceNumber;

    private LocalDate rentDate;

    private LocalDate createDate;

    private LocalDate returnDate;

    private double totalPrice;

    private int totalRentDay;

    private int customerId;

    private String customerFirstName;

    private String customerLastName;
}
