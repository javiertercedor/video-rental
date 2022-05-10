package com.video.rental.njcode.price.infraestructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceResponse {

    private Long id;
    private Double price;
    private Integer daysForPrice;
    private Integer bonusPoints;
}
