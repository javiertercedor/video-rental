package com.video.rental.njcode.renting.infraestructure.jpa.adapter;

import com.video.rental.njcode.film.infraestructure.jpa.model.FilmDto;
import com.video.rental.njcode.renting.domain.Renting;
import com.video.rental.njcode.renting.domain.repository.RentingRepository;
import com.video.rental.njcode.renting.infraestructure.jpa.model.RentingDto;
import com.video.rental.njcode.renting.infraestructure.jpa.repository.RentingJpaRepository;
import com.video.rental.njcode.share.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RentingAdapter implements RentingRepository {

    private final ModelMapper modelMapper;
    private final RentingJpaRepository repository;

    @Override
    public Optional<Renting> save(Renting renting) {
        final RentingDto rentingDtoMapped = generateRentingDtoObject(renting);
        final RentingDto saved = repository.save(rentingDtoMapped);

        return getRenting(saved);
    }

    @Override
    public Optional<Renting> returnRenting(Renting renting) {
        existsById(renting.getId());
        final RentingDto rentingDtoMapped = generateRentingDtoObject(renting);
        final RentingDto saved = repository.save(rentingDtoMapped);

        return getRenting(saved);
    }

    @Override
    public Optional<Renting> get(Long id) {
        final Optional<RentingDto> rentingDto = repository.findById(id);
        return getRenting(rentingDto.get());
    }

    private void existsById(Long id) {
        final boolean exists = repository.existsById(id);
        if (!exists) {
            throw new EntityNotFoundException("Renting not found");
        }
    }

    private Optional<Renting> getRenting(RentingDto renting) {
        List<Long> ids = renting.getFilms().stream().map(item -> item.getId()).collect(Collectors.toList());

        return Optional.of(Renting.createRenting(renting.getId(),
                renting.getCustomer().getId(),
                ids,
                renting.getNumberOfDays(),
                renting.getTotalPrice(),
                renting.getLateCharge(),
                renting.getInitDate()));
    }

    private RentingDto generateRentingDtoObject(Renting renting) {
        final RentingDto rentingDtoMapped = modelMapper.map(renting, RentingDto.class);
        rentingDtoMapped.setFilms(new ArrayList<>());
        renting.getFilms().forEach(item -> rentingDtoMapped.getFilms().add(new FilmDto(item, null, null)));
        return rentingDtoMapped;
    }
}
