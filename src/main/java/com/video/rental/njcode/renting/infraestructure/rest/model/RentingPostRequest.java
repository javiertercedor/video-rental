package com.video.rental.njcode.renting.infraestructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentingPostRequest {
    
    private Long customerId;
    private List<Long> films;
    private Integer numberOfDays;
}
