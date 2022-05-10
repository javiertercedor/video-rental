package com.video.rental.njcode.price.application;

import com.video.rental.njcode.price.domain.Price;
import com.video.rental.njcode.price.domain.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PriceService {

    private final PriceRepository repository;

    public Price save(Price price) {
        return repository.create(price).get();
    }

    public Price modify(Long id, Price price) {
        return repository.modify(id, price).get();
    }

    public Price get(Long id) {
        return repository.get(id).get();
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
