package com.video.rental.njcode.film.infraestructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmResponse {

    private Long id;
    private String name;
    private Long priceId;
}
