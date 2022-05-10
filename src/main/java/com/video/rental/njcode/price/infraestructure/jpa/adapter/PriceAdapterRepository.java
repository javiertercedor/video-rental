package com.video.rental.njcode.price.infraestructure.jpa.adapter;

import com.video.rental.njcode.price.domain.Price;
import com.video.rental.njcode.price.infraestructure.jpa.model.PriceDto;
import com.video.rental.njcode.price.infraestructure.jpa.repository.PriceJpaRepository;
import com.video.rental.njcode.price.domain.repository.PriceRepository;
import com.video.rental.njcode.share.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriceAdapterRepository implements PriceRepository {

    private final ModelMapper modelMapper;
    private final PriceJpaRepository repository;

    @Override
    public Optional<Price> create(Price price) {
        final PriceDto priceSaved = repository.save(modelMapper.map(price, PriceDto.class));

        return getPriceDomain(priceSaved);
    }

    @Override
    public Optional<Price> modify(Long id, Price price) {
        existsById(id);
        final PriceDto priceSaved = repository.save(modelMapper.map(price, PriceDto.class));

        return getPriceDomain(priceSaved);
    }

    @Override
    public Optional<Price> get(Long id) {
        final Optional<PriceDto> optionalPriceDto = repository.findById(id);
        final PriceDto priceDto = optionalPriceDto.orElseThrow(() -> new EntityNotFoundException("Price not found"));

        return getPriceDomain(priceDto);
    }

    @Override
    public void delete(Long id) {
        existsById(id);
        repository.deleteById(id);
    }

    @Override
    public void existsById(Long id) {
        final boolean exists = repository.existsById(id);
        if (!exists) {
            throw new EntityNotFoundException("Price not found");
        }
    }

    private Optional<Price> getPriceDomain(PriceDto priceDto) {
        return Optional.of(Price.createPrice(priceDto.getId(),
                priceDto.getPrice(),
                priceDto.getDaysForPrice(),
                priceDto.getBonusPoints()));
    }
}
