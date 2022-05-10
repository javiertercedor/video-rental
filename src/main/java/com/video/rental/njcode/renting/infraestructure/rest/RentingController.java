package com.video.rental.njcode.renting.infraestructure.rest;

import com.video.rental.njcode.renting.application.RentingService;
import com.video.rental.njcode.renting.domain.Renting;
import com.video.rental.njcode.renting.infraestructure.rest.mapper.RentingToRentingResponse;
import com.video.rental.njcode.renting.infraestructure.rest.model.RentingPostRequest;
import com.video.rental.njcode.renting.infraestructure.rest.model.RentingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/renting")
@AllArgsConstructor
@Slf4j
@SecurityRequirement(name = "njcode")
public class RentingController {
    
    private final RentingService service;

    @Operation(summary = "Create Renting Object")
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
    public ResponseEntity<RentingResponse> create(@Valid @RequestBody RentingPostRequest request) {
        log.info("Renting API Create method");
        return ResponseEntity.ok().body(RentingToRentingResponse.getRentingResponseFromRenting(service.save(Renting.createRenting(request.getCustomerId(), request.getFilms(), request.getNumberOfDays()))));
    }

    @Operation(summary = "Returns Renting Object")
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
    @PutMapping("/returns/{id}")
    public ResponseEntity<RentingResponse> returnFilms(@Valid @PathVariable Long id) {
        log.info("Renting API Returns method");
        return ResponseEntity.ok().body(RentingToRentingResponse.getRentingResponseFromRenting(service.returnFilms(id)));
    }
}
