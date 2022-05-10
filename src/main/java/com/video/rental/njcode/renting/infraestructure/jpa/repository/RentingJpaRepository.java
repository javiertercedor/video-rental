package com.video.rental.njcode.renting.infraestructure.jpa.repository;

import com.video.rental.njcode.renting.infraestructure.jpa.model.RentingDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentingJpaRepository extends JpaRepository<RentingDto, Long> {
}
