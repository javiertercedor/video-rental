package com.video.rental.njcode.price.infraestructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricePutRequest {

    private Long id;
    private Double price;
    private Integer daysForPrice;
    private Integer bonusPoints;
}
