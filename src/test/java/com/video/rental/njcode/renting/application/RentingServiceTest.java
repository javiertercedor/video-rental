package com.video.rental.njcode.renting.application;

import com.video.rental.njcode.customer.domain.CustomerBonusEvent;
import com.video.rental.njcode.film.domain.Film;
import com.video.rental.njcode.film.domain.repository.FilmRepository;
import com.video.rental.njcode.price.domain.Price;
import com.video.rental.njcode.price.domain.repository.PriceRepository;
import com.video.rental.njcode.renting.domain.Renting;
import com.video.rental.njcode.renting.domain.repository.RentingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;

@SpringBootTest
class RentingServiceTest {

    @MockBean
    private RentingRepository repository;
    @MockBean
    private FilmRepository filmRepository;
    @MockBean
    private PriceRepository priceRepository;
    @MockBean
    private RentingBonusEventPublisher eventPublisher;
    
    @Autowired
    private RentingService service;
    
    @Test
    void save_properlyData_shouldWork() {
        //given
        final Renting renting = Renting.createRenting(1L, Arrays.asList(1L), 5);
        final Collection<Film> films = Arrays.asList(Film.createFilm(1L, "Spiderman", 1L));
        final Price price = Price.createPrice(1L, 10.0, 1, 1);
        
        given(filmRepository.getAllByIds(anyList())).willReturn(films);
        given(priceRepository.get(anyLong())).willReturn(Optional.of(price));
        given(repository.save(any(Renting.class))).willReturn(Optional.of(renting));
        willDoNothing().given(eventPublisher).publishBonusCustomerEvent(any(CustomerBonusEvent.class));

        //when
        final Renting rentingSaved = service.save(renting);

        //then
        then(rentingSaved).isNotNull();
        then(renting.getTotalPrice()).isEqualTo(50.0);
        BDDMockito.then(repository).should().save(renting);
        BDDMockito.then(eventPublisher).should(times(1)).publishBonusCustomerEvent(any(CustomerBonusEvent.class));
    }

    @Test
    void save_nullRentingObject_shouldThrowsException() {
        //when
        final Throwable exception = catchThrowable(() -> service.save(null));
        //then
        then(exception).isInstanceOf(NullPointerException.class);
    }

    @Test
    void returnFilms_properlyData_shouldWork() {
        //given
        final Renting renting = Renting.createRenting(1L,1L, Arrays.asList(1L), 5, 50.0, 50.0, getDateBefore());
        final Collection<Film> films = Arrays.asList(Film.createFilm(1L, "Spiderman", 1L));
        final Price price = Price.createPrice(1L, 10.0, 1, 1);
        
        given(repository.get(anyLong())).willReturn(Optional.of(renting));
        given(repository.save(any(Renting.class))).willReturn(Optional.of(renting));
        given(filmRepository.getAllByIds(anyList())).willReturn(films);
        given(priceRepository.get(anyLong())).willReturn(Optional.of(price));
        
        //when
        final Renting rentingReturned = service.returnFilms(1L);
        
        //then
        then(rentingReturned).isNotNull();
        then(rentingReturned.getLateCharge()).isEqualTo(50.0);
    }

    @Test
    void save_notExistId_shouldThrowsNoSuchElementException() {
        //when
        final Throwable exception = catchThrowable(() -> service.returnFilms(null));
        //then
        then(exception).isInstanceOf(NoSuchElementException.class);
    }

    private Date getDateBefore() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -10);
        
        return cal.getTime();
    }
}