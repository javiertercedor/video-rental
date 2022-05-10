package com.video.rental.njcode.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBonusEvent implements Serializable {
    
    private Long customerId;
    private Integer bonusPoints;
}
