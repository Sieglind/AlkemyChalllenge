package ar.com.cdmoraleda.alkemychallenge.controller;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.services.MovieService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping
    Movie createMovie(@RequestBody MovieDto movieDto) {
        return movieService.createMovie(movieDto);
    }

    @PutMapping
    Movie updateMovie(@RequestBody MovieDto movieDto, @RequestParam Integer id){
        return movieService.updateMovie(movieDto,id);
    }
}