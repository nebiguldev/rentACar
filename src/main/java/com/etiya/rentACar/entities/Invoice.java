package com.etiya.rentACar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bills")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "total_rent_day")
    private int totalRentDay;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne//bir müşterinin birden fazla faturası olabilir.-->karşılığı customerde
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne//Billmany,one rental-->birden fazla fatura bir kiralamada olabilir-->karşılığı rentalde
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @OneToOne(mappedBy = "invoice")
    private Payment payment;
}

