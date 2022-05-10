package com.video.rental.njcode.film.domain.repository;

import com.video.rental.njcode.film.domain.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmRepository {
    
    Optional<Film> create(Film film);
    Optional<Film> modify(Long id, Film film);
    Optional<Film> get(Long id);
    Collection<Film> getAllByIds(Collection<Long> ids);
    void delete(Long id);
    
}
