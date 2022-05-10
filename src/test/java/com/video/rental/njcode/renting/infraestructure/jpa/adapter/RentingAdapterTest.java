package com.video.rental.njcode.renting.infraestructure.jpa.adapter;

import com.video.rental.njcode.customer.infraestructure.jpa.model.CustomerDto;
import com.video.rental.njcode.film.infraestructure.jpa.model.FilmDto;
import com.video.rental.njcode.renting.domain.Renting;
import com.video.rental.njcode.renting.infraestructure.jpa.model.RentingDto;
import com.video.rental.njcode.renting.infraestructure.jpa.repository.RentingJpaRepository;
import com.video.rental.njcode.share.domain.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class RentingAdapterTest {

    @MockBean
    private RentingJpaRepository repository;
    
    @Autowired
    private RentingAdapter adapter;

    @Test
    void save_properlyData_shouldWork() {
        //given
        given(repository.save(any(RentingDto.class))).willReturn(createRentingDto());

        //when
        final Optional<Renting> rentingSaved = adapter.save(createRenting());

        //then
        then(rentingSaved.isPresent()).isTrue();
        then(rentingSaved.get().getTotalPrice()).isEqualTo(1.0);
    }

    @Test
    void returnRenting_properlyData_shouldWork() {
        //given
        given(repository.save(any(RentingDto.class))).willReturn(createRentingDto());
        given(repository.existsById(anyLong())).willReturn(Boolean.TRUE);

        //when
        final Optional<Renting> rentingSaved = adapter.returnRenting(createRenting());

        //then
        then(rentingSaved.isPresent()).isTrue();
        then(rentingSaved.get().getTotalPrice()).isEqualTo(1.0);
    }

    @Test
    void returnRenting_rentingDoesNotExist_shouldThrowEntityNotFoundException() {
        //given
        given(repository.existsById(anyLong())).willReturn(Boolean.FALSE);

        //when
        final Throwable exception = catchThrowable(() -> adapter.returnRenting(createRenting()));

        //then
        then(exception).isInstanceOf(EntityNotFoundException.class);
    }

    private Renting createRenting() {
        return Renting.createRenting(1l, 1l, Arrays.asList(1l), 1, 1.0, 1.0, new Date());
    }

    private RentingDto createRentingDto() {
        RentingDto rentingDto = new RentingDto();
        rentingDto.setId(1L);
        rentingDto.setNumberOfDays(1);
        rentingDto.setTotalPrice(1.0);
        rentingDto.setLateCharge(1.0);
        rentingDto.setInitDate(new Date());
        rentingDto.setCustomer(new CustomerDto());
        rentingDto.setFilms(Arrays.asList(new FilmDto()));

        return rentingDto;
    }
}