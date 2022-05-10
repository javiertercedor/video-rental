package com.video.rental.njcode.film.infraestructure.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto {
    
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String name;
    @Min(0)
    private Long priceId;
}
