package com.video.rental.njcode.customer.application;

import com.video.rental.njcode.customer.domain.Customer;
import com.video.rental.njcode.customer.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public Customer save(Customer customer) {
        return repository.create(customer).get();
    }

    public Customer modify(Long id, Customer customer) {
        return repository.modify(id, customer).get();
    }

    public Customer get(Long id) {
        return repository.get(id).get();
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
