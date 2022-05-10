package com.video.rental.njcode.film.infraestructure.jpa.repository;

import com.video.rental.njcode.film.infraestructure.jpa.model.FilmDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmJpaRepository extends JpaRepository<FilmDto, Long> {
}
