package ar.com.cdmoraleda.alkemychallenge.controller;

import ar.com.cdmoraleda.alkemychallenge.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.dto.FoundMovie;
import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping
    Movie createMovie(@RequestBody MovieDto movieDto) {
        return movieService.createMovie(movieDto);
    }

    @GetMapping(params = {"name"})
    FoundMovie findMovieByTitle(@RequestParam String name) {
        return movieService.findMovieByTitle(name);
    }

    @GetMapping(params = {"genreId", "order"})
    List<FoundMovie> filterMoviesByGenre(@RequestParam Integer genreId, @RequestParam String order) {
        return movieService.filterMoviesByGenre(genreId, order);
    }

    @PutMapping
    Movie updateMovie(@RequestBody MovieDto movieDto, @RequestParam Integer id) {
        return movieService.updateMovie(movieDto, id);
    }

    @DeleteMapping
    void deleteMovie(@RequestParam Integer movieId) {
        movieService.deleteMovie(movieId);
    }

    @PostMapping("/{movieId}/characters/{characterId}")
    void addCharacterToMovie(@PathVariable Integer movieId, @PathVariable Integer characterId) {
        movieService.addCharacterToMovie(movieId, characterId);
    }

    @DeleteMapping("/{movieId}/characters/{characterId}")
    void removeCharacterFromMovie(@PathVariable Integer movieId, @PathVariable Integer characterId) {
        movieService.removeCharacterFromMovie(movieId, characterId);
    }
}