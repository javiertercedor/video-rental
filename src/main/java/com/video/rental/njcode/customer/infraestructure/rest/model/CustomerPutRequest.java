package com.video.rental.njcode.customer.infraestructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPutRequest {

    private String name;
    private String surname;
    private Integer age;
    private Integer bonusPoints;
}
