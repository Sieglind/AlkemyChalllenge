package ar.com.cdmoraleda.alkemychallenge.database.controllers;

import ar.com.cdmoraleda.alkemychallenge.database.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.database.dto.FoundMovie;
import ar.com.cdmoraleda.alkemychallenge.database.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping
    ResponseEntity<Movie> createMovie(@Valid @RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(movieService.createMovie(movieDto),CREATED);
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