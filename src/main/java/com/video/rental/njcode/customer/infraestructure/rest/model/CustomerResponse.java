package com.video.rental.njcode.customer.infraestructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private Integer bonusPoints;
}
