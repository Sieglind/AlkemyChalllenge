package ar.com.cdmoraleda.alkemychallenge.controller;

import ar.com.cdmoraleda.alkemychallenge.dto.GenreDto;
import ar.com.cdmoraleda.alkemychallenge.models.Genre;
import ar.com.cdmoraleda.alkemychallenge.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    GenreService genreService;

    @PostMapping
    Genre createGenre(@RequestBody GenreDto genreDto){
        return genreService.createGenre(genreDto);
    }
}
