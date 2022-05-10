package com.video.rental.njcode.film.infraestructure.rest.mapper;

import com.video.rental.njcode.film.domain.Film;
import com.video.rental.njcode.film.infraestructure.rest.model.FilmResponse;

public class FilmToFilmResponse {

    public static FilmResponse getFilmResponseByFilm(Film film) {
        return FilmResponse.builder()
                .id(film.getId())
                .name(film.getName())
                .priceId(film.getPriceId())
                .build();
    }
}
