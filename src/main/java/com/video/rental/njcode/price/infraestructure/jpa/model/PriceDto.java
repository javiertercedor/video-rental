package com.video.rental.njcode.price.infraestructure.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {
    
    @Id
    @GeneratedValue
    private Long id;
    @Min(0)
    private Double price;
    @Min(0)
    private Integer daysForPrice;
    @Min(0)
    private Integer bonusPoints;
}
