package com.video.rental.njcode.film.infraestructure.rest;

import com.video.rental.njcode.film.application.FilmService;
import com.video.rental.njcode.film.domain.Film;
import com.video.rental.njcode.film.infraestructure.rest.mapper.FilmToFilmResponse;
import com.video.rental.njcode.film.infraestructure.rest.model.FilmPostRequest;
import com.video.rental.njcode.film.infraestructure.rest.model.FilmPutRequest;
import com.video.rental.njcode.film.infraestructure.rest.model.FilmResponse;
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
@RequestMapping("/film")
@AllArgsConstructor
@Slf4j
@SecurityRequirement(name = "njcode")
public class FilmController {

    private final FilmService service;

    @Operation(summary = "Create Film Object")
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
    public ResponseEntity<FilmResponse> create(@Valid @RequestBody FilmPostRequest request) {
        log.info("Film API Create method");
        return ResponseEntity.ok().body(FilmToFilmResponse.getFilmResponseByFilm(service.save(Film.createFilm(null, request.getName(), request.getPriceId()))));
    }

    @Operation(summary = "Put Film Object")
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
    public ResponseEntity<FilmResponse> modify(@Valid @RequestBody FilmPutRequest request, @PathVariable Long id) {
        log.info("Film API modify method");
        return ResponseEntity.ok().body(FilmToFilmResponse.getFilmResponseByFilm(service.modify(id, Film.createFilm(null, request.getName(), request.getPriceId()))));
    }

    @Operation(summary = "Get Film Object")
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
    public ResponseEntity<FilmResponse> get(@PathVariable Long id) {
        log.info("Film API Get method");
        final Film film = service.get(id);

        return ResponseEntity.ok().body(FilmToFilmResponse.getFilmResponseByFilm(film));
    }

    @Operation(summary = "Delete Film Object")
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
        log.info("Film API Delete method");
        service.delete(id);
    }
}
