package com.video.rental.njcode.price.domain.repository;

import com.video.rental.njcode.customer.domain.Customer;
import com.video.rental.njcode.price.domain.Price;

import java.util.Optional;

public interface PriceRepository {

    Optional<Price> create(Price price);

    Optional<Price> modify(Long id, Price price);

    Optional<Price> get(Long id);

    void delete(Long id);

    void existsById(Long id);
}
