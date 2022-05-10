package com.video.rental.njcode.renting.domain.repository;

import com.video.rental.njcode.renting.domain.Renting;

import java.util.Optional;

public interface RentingRepository {

    Optional<Renting> save(Renting renting);

    Optional<Renting> returnRenting(Renting renting);

    Optional<Renting> get(Long id);
}
