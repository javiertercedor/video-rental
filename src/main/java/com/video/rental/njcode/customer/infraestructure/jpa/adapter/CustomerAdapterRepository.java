package com.video.rental.njcode.customer.infraestructure.jpa.adapter;

import com.video.rental.njcode.customer.domain.Customer;
import com.video.rental.njcode.share.domain.exception.EntityNotFoundException;
import com.video.rental.njcode.customer.domain.repository.CustomerRepository;
import com.video.rental.njcode.customer.infraestructure.jpa.model.CustomerDto;
import com.video.rental.njcode.customer.infraestructure.jpa.repository.CustomerJpaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerAdapterRepository implements CustomerRepository {

    private final ModelMapper modelMapper;
    private final CustomerJpaRepository repository;

    @Override
    public Optional<Customer> create(Customer customer) {
        final CustomerDto customerSaved = repository.save(modelMapper.map(customer, CustomerDto.class));

        return getCustomerDomain(customerSaved);
    }

    @Override
    public Optional<Customer> modify(Long id, Customer customer) {
        existsById(id);
        final CustomerDto customerSaved = repository.save(modelMapper.map(customer, CustomerDto.class));

        return getCustomerDomain(customerSaved);
    }

    @Override
    public Optional<Customer> get(Long id) {
        final Optional<CustomerDto> customerDtoOptional = repository.findById(id);
        final CustomerDto customerDto = customerDtoOptional.orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        return getCustomerDomain(customerDto);
    }

    @Override
    public void delete(Long id) {
        existsById(id);
        repository.deleteById(id);
    }

    private Optional<Customer> getCustomerDomain(CustomerDto customerDto) {
        return Optional.of(Customer.createCustomer(customerDto.getId(),
                customerDto.getName(),
                customerDto.getSurname(),
                customerDto.getAge(),
                customerDto.getBonusPoints()));
    }

    private void existsById(Long id) {
        final boolean exists = repository.existsById(id);
        if (!exists) {
            throw new EntityNotFoundException("Customer not found");
        }
    }
}
