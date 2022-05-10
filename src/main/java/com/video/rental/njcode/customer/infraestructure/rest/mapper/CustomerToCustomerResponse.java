package com.video.rental.njcode.customer.infraestructure.rest.mapper;

import com.video.rental.njcode.customer.domain.Customer;
import com.video.rental.njcode.customer.infraestructure.rest.model.CustomerResponse;

public class CustomerToCustomerResponse {

    public static CustomerResponse getCustomerResponseByCustomer(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .age(customer.getAge())
                .bonusPoints(customer.getBonusPoints())
                .build();
    }
}
