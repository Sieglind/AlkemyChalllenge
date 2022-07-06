package ar.com.cdmoraleda.alkemychallenge.controller;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public Movie createMovie(@RequestBody MovieDto movie) {
        return movieService.createMovie(movie);
    }

    @GetMapping
    public List<Movie> findMovie(@RequestParam String title) {
        return movieService.findMovie(title);
    }
}