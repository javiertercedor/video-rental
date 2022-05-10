package com.video.rental.njcode.renting.application;

import com.video.rental.njcode.customer.domain.CustomerBonusEvent;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RentingBonusEvent extends ApplicationEvent {
    
    private CustomerBonusEvent eventObject;
    
    public RentingBonusEvent(Object source, CustomerBonusEvent eventObject) {
        super(source);
        this.eventObject = eventObject;
    }
}
