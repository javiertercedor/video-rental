package com.video.rental.njcode.film.domain;

import com.video.rental.njcode.share.domain.exception.DomainDataException;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class Film {

    private Long id;
    private String name;
    private Long priceId;

    public static Film createFilm(Long id, String name, Long priceId) {
        return new Film(id, name, priceId);
    }

    private Film(Long id, String name, Long priceId) {
        setId(id);
        setName(name);
        setPriceId(priceId);
    }

    public void setId(Long id) {
        this.id = id;
    }

    private void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public void setName(String name) {
        if (!StringUtils.hasLength(name)) {
            throw new DomainDataException("Name field must contains any character");
        }
        this.name = name;
    }
}
