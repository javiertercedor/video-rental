package com.video.rental.njcode.renting.domain;

import com.video.rental.njcode.share.domain.exception.DomainDataException;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class Renting {

    private Long id;
    private Long customerId;
    private List<Long> films;
    private Integer numberOfDays;
    private Double totalPrice;
    private Double lateCharge;
    private Date initDate;

    public static Renting createRenting(Long customerId, List<Long> filmId, Integer numberOfDays) {
        return new Renting(null, customerId, filmId, numberOfDays, 0.0, 0.0, null);
    }

    public static Renting createRenting(Long id, Long customerId, List<Long> filmId, Integer numberOfDays, Double totalPrice, Double lateCharge, Date initDate) {
        return new Renting(id, customerId, filmId, numberOfDays, totalPrice, lateCharge, initDate);
    }

    private Renting(Long id, Long customerId, List<Long> films, Integer numberOfDays, Double totalPrice, Double lateCharge, Date initDate) {
        this.setId(id);
        this.setCustomerId(customerId);
        this.setFilms(films);
        this.setNumberOfDays(numberOfDays);
        this.setTotalPrice(totalPrice);
        this.setLateCharge(lateCharge);
        this.setInitDate(initDate);
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    private void setFilms(List<Long> films) {
        this.films = films;
    }

    private void setNumberOfDays(Integer numberOfDays) {
        if (numberOfDays == null || numberOfDays < 1) {
            throw new DomainDataException("Number of days must be greater than 1");
        }
        this.numberOfDays = numberOfDays;
    }

    public void setTotalPrice(Double totalPrice) {
        if (totalPrice == null) {
            throw new DomainDataException("Total price must contain a value");
        }
        this.totalPrice = totalPrice;
    }

    public void setLateCharge(Double lateCharge) {
        if (lateCharge == null) {
            throw new DomainDataException("Late Charge must contain a value");
        }
        this.lateCharge = lateCharge;
    }

    private void setInitDate(Date initDate) {
        this.initDate = initDate;
    }
}
