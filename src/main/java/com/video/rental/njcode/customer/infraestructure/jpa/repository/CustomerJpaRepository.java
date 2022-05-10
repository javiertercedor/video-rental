package com.video.rental.njcode.customer.infraestructure.jpa.repository;

import com.video.rental.njcode.customer.infraestructure.jpa.model.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerDto, Long> {
}
