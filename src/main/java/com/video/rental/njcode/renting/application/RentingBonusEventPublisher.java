package com.video.rental.njcode.renting.application;

import com.video.rental.njcode.customer.domain.CustomerBonusEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RentingBonusEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishBonusCustomerEvent(final CustomerBonusEvent eventObject) {
        RentingBonusEvent rentingBonusEvent = new RentingBonusEvent(this, eventObject);
        applicationEventPublisher.publishEvent(rentingBonusEvent);
    }
}
