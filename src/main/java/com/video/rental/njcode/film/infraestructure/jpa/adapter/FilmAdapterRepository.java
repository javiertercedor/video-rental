package com.video.rental.njcode.film.infraestructure.jpa.adapter;

import com.video.rental.njcode.film.domain.Film;
import com.video.rental.njcode.film.domain.repository.FilmRepository;
import com.video.rental.njcode.film.infraestructure.jpa.model.FilmDto;
import com.video.rental.njcode.film.infraestructure.jpa.repository.FilmJpaRepository;
import com.video.rental.njcode.price.infraestructure.jpa.adapter.PriceAdapterRepository;
import com.video.rental.njcode.share.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FilmAdapterRepository implements FilmRepository {

    private final ModelMapper modelMapper;
    private final FilmJpaRepository repository;
    private final PriceAdapterRepository priceAdapterRepository;

    @Override
    public Optional<Film> create(Film film) {
        priceAdapterRepository.existsById(film.getPriceId());
        final FilmDto filmSaved = repository.save(modelMapper.map(film, FilmDto.class));

        return getFilmDomain(filmSaved);
    }

    @Override
    public Optional<Film> modify(Long id, Film film) {
        existsById(id);
        final FilmDto filmSaved = repository.save(modelMapper.map(film, FilmDto.class));

        return getFilmDomain(filmSaved);
    }

    @Override
    public Optional<Film> get(Long id) {
        final Optional<FilmDto> optionalFilmDto = repository.findById(id);
        final FilmDto filmDto = optionalFilmDto.orElseThrow(() -> new EntityNotFoundException("Film not found"));

        return getFilmDomain(filmDto);
    }

    @Override
    public Collection<Film> getAllByIds(Collection<Long> ids) {
        Collection<Film> films = new HashSet<>();
        repository.findAllById(ids).forEach(item -> films.add(createFilm(item)));

        return films;
    }

    @Override
    public void delete(Long id) {
        existsById(id);
        repository.deleteById(id);
    }

    private Optional<Film> getFilmDomain(FilmDto filmDto) {
        return Optional.of(Film.createFilm(filmDto.getId(),
                filmDto.getName(),
                filmDto.getPriceId()));
    }

    private void existsById(Long id) {
        final boolean exists = repository.existsById(id);
        if (!exists) {
            throw new EntityNotFoundException("Film not found");
        }
    }

    private Film createFilm(FilmDto filmDto) {
        return Film.createFilm(filmDto.getId(),
                filmDto.getName(),
                filmDto.getPriceId());
    }
}
