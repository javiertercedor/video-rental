package com.video.rental.njcode.price.infraestructure.rest;

import com.video.rental.njcode.price.application.PriceService;
import com.video.rental.njcode.price.domain.Price;
import com.video.rental.njcode.price.infraestructure.rest.mapper.PriceToPriceResponse;
import com.video.rental.njcode.price.infraestructure.rest.model.PricePostRequest;
import com.video.rental.njcode.price.infraestructure.rest.model.PricePutRequest;
import com.video.rental.njcode.price.infraestructure.rest.model.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/price")
@AllArgsConstructor
@Slf4j
@SecurityRequirement(name = "njcode")
public class PriceController {

    private final PriceService service;

    @Operation(summary = "Create Price Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success Request",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Content not authorized",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<PriceResponse> create(@Valid @RequestBody PricePostRequest request) {
        log.info("Price API Create method");
        return ResponseEntity.ok().body(PriceToPriceResponse.getPriceResponseByPrice(service.save(Price.createPrice(request.getPrice(), request.getDaysForPrice(), request.getBonusPoints()))));
    }

    @Operation(summary = "Update Price Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success Request",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Content not authorized",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PriceResponse> modify(@Valid @RequestBody PricePutRequest request, @PathVariable Long id) {
        log.info("Price API Modify method");
        return ResponseEntity.ok().body(PriceToPriceResponse.getPriceResponseByPrice(service.modify(id, Price.createPrice(id, request.getPrice(), request.getDaysForPrice(), request.getBonusPoints()))));
    }

    @Operation(summary = "Obtain Price Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success Request",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Content not authorized",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PriceResponse> get(@PathVariable Long id) {
        log.info("Price API Get method");
        final Price price = service.get(id);

        return ResponseEntity.ok().body(PriceToPriceResponse.getPriceResponseByPrice(price));
    }

    @Operation(summary = "Delete Price Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success Request",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Content not authorized",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Price API Delete method");
        service.delete(id);
    }
}
