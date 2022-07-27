package ar.com.cdmoraleda.alkemychallenge.controllers;

import ar.com.cdmoraleda.alkemychallenge.services.MovieService;
import ar.com.cdmoraleda.alkemychallenge.utilities.OnCreateMovie;
import ar.com.cdmoraleda.alkemychallenge.utilities.OnUpdateMovie;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping
    @Validated(OnCreateMovie.class)
    ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(movieService.createMovie(movieDto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<Set<MovieDto>> findMovieByTitle(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer genreId,
            @RequestParam(required = false) String order) {
        return new ResponseEntity<>(movieService.findBy(name, genreId, order), HttpStatus.OK);
    }

    @PutMapping
    @Validated(OnUpdateMovie.class)
    ResponseEntity<MovieDto> updateMovie(@Valid @RequestBody MovieDto movieDto, @RequestParam Integer movieId) {
        return new ResponseEntity<>(movieService.updateMovie(movieDto, movieId), HttpStatus.OK);
    }

    @DeleteMapping
    ResponseEntity<String> deleteMovie(@RequestParam Integer movieId) {
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
    }

    @PostMapping("/{movieId}/characters/{characterId}")
    ResponseEntity<String> addCharacterToMovie(@PathVariable Integer movieId, @PathVariable Integer characterId) {
        movieService.addCharacterToMovie(movieId, characterId);
        return new ResponseEntity<>("Character successfully added",HttpStatus.OK);
    }

    @DeleteMapping("/{movieId}/characters/{characterId}")
    ResponseEntity<String> removeCharacterFromMovie(@PathVariable Integer movieId, @PathVariable Integer characterId) {
        movieService.removeCharacterFromMovie(movieId, characterId);
        return new ResponseEntity<>("Character successfully removed",HttpStatus.OK);
    }
}