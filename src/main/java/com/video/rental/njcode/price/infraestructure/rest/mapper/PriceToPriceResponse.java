package com.video.rental.njcode.price.infraestructure.rest.mapper;

import com.video.rental.njcode.price.domain.Price;
import com.video.rental.njcode.price.infraestructure.rest.model.PriceResponse;

public class PriceToPriceResponse {

    public static PriceResponse getPriceResponseByPrice(Price price) {
        return PriceResponse.builder()
                .id(price.getId())
                .price(price.getPrice())
                .daysForPrice(price.getDaysForPrice())
                .bonusPoints(price.getBonusPoints())
                .build();
    }
}
