package ar.com.cdmoraleda.alkemychallenge.database.controllers;

import ar.com.cdmoraleda.alkemychallenge.database.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.database.dto.MovieDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping
    ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(movieService.createMovie(movieDto), HttpStatus.CREATED);
    }

    @GetMapping(params = {"name", "order"})
    ResponseEntity<List<MovieDto>> findMovieByTitle(@RequestParam String name,@RequestParam String order) {
        return new ResponseEntity<>(movieService.findMovieByTitle(name,order), HttpStatus.OK);
    }

    @GetMapping(params = {"genreId", "order"})
    ResponseEntity<List<MovieDto>> filterMoviesByGenre(@RequestParam Integer genreId, @RequestParam String order) {
        return new ResponseEntity<>(movieService.filterMoviesByGenre(genreId, order),HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movieDto, @RequestParam Integer movieId) {
        return new ResponseEntity<>(movieService.updateMovie(movieDto, movieId), HttpStatus.OK);
    }

    @DeleteMapping
    ResponseEntity<String> deleteMovie(@RequestParam Integer movieId) {
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
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