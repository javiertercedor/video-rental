package com.video.rental.njcode.renting.infraestructure.rest.mapper;

import com.video.rental.njcode.renting.domain.Renting;
import com.video.rental.njcode.renting.infraestructure.rest.model.RentingResponse;

public class RentingToRentingResponse {

    public static RentingResponse getRentingResponseFromRenting(Renting renting) {
        return RentingResponse.builder().id(renting.getId())
                .customerId(renting.getCustomerId())
                .films(renting.getFilms())
                .numberOfDays(renting.getNumberOfDays())
                .initDate(renting.getInitDate())
                .lateCharge(renting.getLateCharge())
                .totalPrice(renting.getTotalPrice()).build();
    }
}
