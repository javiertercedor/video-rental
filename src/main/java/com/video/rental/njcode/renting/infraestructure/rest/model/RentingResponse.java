package com.video.rental.njcode.renting.infraestructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentingResponse {

    private Long id;
    private Long customerId;
    private List<Long> films;
    private Integer numberOfDays;
    private Double totalPrice;
    private Double lateCharge;
    private Date initDate;
}
