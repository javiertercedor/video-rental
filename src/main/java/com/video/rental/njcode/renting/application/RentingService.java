package com.video.rental.njcode.renting.application;

import com.video.rental.njcode.customer.domain.CustomerBonusEvent;
import com.video.rental.njcode.film.domain.Film;
import com.video.rental.njcode.film.domain.repository.FilmRepository;
import com.video.rental.njcode.price.domain.Price;
import com.video.rental.njcode.price.domain.repository.PriceRepository;
import com.video.rental.njcode.renting.domain.Renting;
import com.video.rental.njcode.renting.domain.repository.RentingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collection;

@Service
@AllArgsConstructor
public class RentingService {

    private final RentingRepository repository;
    private final FilmRepository filmRepository;
    private final PriceRepository priceRepository;
    private final RentingBonusEventPublisher eventPublisher;

    public Renting save(Renting renting) {
        final CustomerBonusEvent customerEventObject = createCustomerEventObject(renting.getCustomerId());

        rentingCalculations(renting, customerEventObject);

        eventPublisher.publishBonusCustomerEvent(customerEventObject);

        return repository.save(renting).get();
    }

    public Renting returnFilms(Long id) {
        final Renting renting = returnRentingOperations(id);

        repository.save(renting);
        
        return renting;
    }

    private Renting returnRentingOperations(Long id) {
        final Renting renting = repository.get(id).get();

        returnRentingCalculations(renting);

        return renting;
    }

    private void returnRentingCalculations(Renting renting) {
        final Collection<Film> films = filmRepository.getAllByIds(renting.getFilms());
        Integer extraDays = calculateExtraDays(renting);
        renting.setLateCharge(films.stream().mapToDouble(item -> calculateLatePayment(item, extraDays)).sum());
    }

    private Integer calculateExtraDays(Renting renting) {
        Period period = Period.between(LocalDate.now(), renting.getInitDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        Integer extraDays = period.getDays() - renting.getNumberOfDays() > 0 ? period.getDays() : 0;
        return extraDays;
    }

    private double calculateLatePayment(Film item, Integer extraDays) {
        Price price = priceRepository.get(item.getPriceId()).get();
        return price.getPrice() * extraDays;
    }

    private double calculateInitialPaymentAndBonusPoints(Renting renting, Film item, Integer extraDays, CustomerBonusEvent eventObject) {
        Price price = priceRepository.get(item.getPriceId()).get();
        addingCustomerBonusPoints(renting, eventObject, price);

        Integer daysToAdd = (renting.getNumberOfDays() - price.getDaysForPrice()) + extraDays;
        return price.getPrice() * (daysToAdd > 0 ? (daysToAdd + 1) : 1);
    }

    private void addingCustomerBonusPoints(Renting renting, CustomerBonusEvent eventObject, Price price) {
        eventObject.setBonusPoints(eventObject.getBonusPoints() + (price.getBonusPoints() * renting.getNumberOfDays()));
    }

    private CustomerBonusEvent createCustomerEventObject(Long customerId) {
        CustomerBonusEvent eventObject = new CustomerBonusEvent();
        eventObject.setCustomerId(customerId);
        eventObject.setBonusPoints(0);

        return eventObject;
    }

    private void rentingCalculations(Renting renting, CustomerBonusEvent customerEventObject) {
        final Collection<Film> films = filmRepository.getAllByIds(renting.getFilms());
        renting.setTotalPrice(films.stream().mapToDouble(item -> calculateInitialPaymentAndBonusPoints(renting, item, 0, customerEventObject)).sum());
    }
}
