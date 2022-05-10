package com.video.rental.njcode.price.infraestructure.jpa.repository;

import com.video.rental.njcode.price.infraestructure.jpa.model.PriceDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceJpaRepository extends JpaRepository<PriceDto, Long> {
}
