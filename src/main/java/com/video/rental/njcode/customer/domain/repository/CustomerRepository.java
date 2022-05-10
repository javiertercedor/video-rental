package com.video.rental.njcode.customer.domain.repository;

import com.video.rental.njcode.customer.domain.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> create(Customer customer);

    Optional<Customer> modify(Long id, Customer customer);

    Optional<Customer> get(Long id);

    void delete(Long id);
}
