package com.video.rental.njcode.customer.application;

import com.video.rental.njcode.customer.domain.Customer;
import com.video.rental.njcode.renting.application.RentingBonusEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class CustomerEventListener implements ApplicationListener<RentingBonusEvent> {

    private final CustomerService service;

    @Override
    public void onApplicationEvent(RentingBonusEvent event) {
        log.info("Bonus event init for Customer: " + event.getEventObject().getCustomerId());

        final Customer customer = service.get(event.getEventObject().getCustomerId());
        customer.setBonusPoints(Integer.valueOf(event.getEventObject().getBonusPoints()));

        service.save(customer);

        log.info("Bonus updated for customer: " + event.getEventObject().getCustomerId());
    }
}
