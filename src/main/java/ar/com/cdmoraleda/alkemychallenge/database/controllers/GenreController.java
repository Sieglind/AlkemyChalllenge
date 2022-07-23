package ar.com.cdmoraleda.alkemychallenge.database.controllers;

import ar.com.cdmoraleda.alkemychallenge.database.dto.GenreDto;
import ar.com.cdmoraleda.alkemychallenge.database.models.Genre;
import ar.com.cdmoraleda.alkemychallenge.database.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    GenreService genreService;

    @PostMapping
    ResponseEntity<Genre> createGenre(@Valid @RequestBody GenreDto genreDto) {
        return new ResponseEntity<>(genreService.createGenre(genreDto), HttpStatus.CREATED);
    }
}