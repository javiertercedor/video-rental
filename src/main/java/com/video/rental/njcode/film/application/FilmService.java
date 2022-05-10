package com.video.rental.njcode.film.application;

import com.video.rental.njcode.film.domain.Film;
import com.video.rental.njcode.film.domain.repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilmService {

    private final FilmRepository repository;

    public Film save(Film film) {
        return repository.create(film).get();
    }

    public Film modify(Long id, Film film) {
        return repository.modify(id, film).get();
    }

    public Film get(Long id) {
        return repository.get(id).get();
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
