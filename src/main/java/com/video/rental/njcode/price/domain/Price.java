package com.video.rental.njcode.price.domain;

import com.video.rental.njcode.share.domain.exception.DomainDataException;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class Price {

    private Long id;
    private Double price;
    private Integer daysForPrice;
    private Integer bonusPoints;

    public static Price createPrice(Double price, Integer daysForPrice, Integer bonusPoints) {
        return new Price(null, price, daysForPrice, bonusPoints);
    }

    public static Price createPrice(Long id, Double price, Integer daysForPrice, Integer bonusPoints) {
        return new Price(id, price, daysForPrice, bonusPoints);
    }

    private Price(Long id, Double price, Integer daysForPrice, Integer bonusPoints) {
        this.setId(id);
        this.setPrice(price);
        this.setDaysForPrice(daysForPrice);
        this.setBonusPoints(bonusPoints);
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setPrice(Double price) {
        if (isProperlyPrice(price)) {
            throw new DomainDataException("Price field must be greater than 0");
        }
        this.price = price;
    }

    private void setBonusPoints(Integer bonusPoints) {
        if (bonusPoints < 0) {
            throw new DomainDataException("Bonus Points must be greater than 0");
        }
        this.bonusPoints = bonusPoints;
    }

    private void setDaysForPrice(Integer daysForPrice) {
        this.daysForPrice = daysForPrice;
    }

    private boolean isProperlyPrice(Double price) {
        return price == null || price < 0;
    }
}
