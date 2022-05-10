package com.video.rental.njcode.film.infraestructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmPostRequest {

    private String name;
    private Long priceId;
}
