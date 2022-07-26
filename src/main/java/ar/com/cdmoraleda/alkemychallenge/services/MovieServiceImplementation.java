package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.Repositories.IMovieRepository;
import ar.com.cdmoraleda.alkemychallenge.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImplementation implements MovieService {
    @Autowired
    IMovieRepository movieRepository;

    @Autowired
    CharacterService characterService;

    @Autowired
    GenreService genreService;

    public MovieDto createMovie(MovieDto movieDto) {
        if (movieRepository.findByTitle(movieDto.getTitle()).isPresent()) {
            return movieDto;
        }
        Movie savedMovie = movieRepository.save(new Movie(movieDto));
        movieDto.getAssocCharacters().forEach((characterDto) -> {
            characterService.createCharacter(characterDto, savedMovie);
        });
        return new MovieDto(movieRepository.save(savedMovie), true);
    }

    public List<MovieDto> findBy(String name, Integer genreId, String order) {
        Genre genre = genreService.findById(genreId);
        List<Movie> movies;
        if (order == null || order.isBlank()) {
            if (genre == null) {
                movies = movieRepository.findByTitleContaining(name);
            } else if (name == null || name.isBlank()) {
                movies = movieRepository.findByAssocGenres(genre);
            } else {
                movies = movieRepository.findByTitleContainingAndAssocGenres(name, genre);
            }
        } else {
            if (genre == null) {
                movies = movieRepository.findByTitleContaining(name,Sort.by(Sort.Direction.fromString(order), "releaseYear"));
            } else if (name == null || name.isBlank()) {
                movies = movieRepository.findByAssocGenres(genre, Sort.by(Sort.Direction.fromString(order), "releaseYear"));
            } else {
                movies = movieRepository.findByTitleContainingAndAssocGenres(name, genre, Sort.by(Sort.Direction.fromString(order), "releaseYear"));
            }
        }
        return movies.stream().map(MovieDto::new).collect(Collectors.toList());
    }

    public MovieDto updateMovie(MovieDto movieDto, Integer movieId) {
        Movie updatedMovie = new Movie(movieDto, movieRepository.findById(movieId).orElseThrow());
        return new MovieDto(movieRepository.save(updatedMovie), true);
    }

    public void deleteMovie(Integer movieId) {
        Movie movieToDelete = movieRepository.findById(movieId).orElseThrow();
        movieToDelete.getAssocCharacters().forEach((character) -> {
            character.removeMovie(movieToDelete);
            characterService.save(character);
        });
        movieToDelete.getAssocCharacters().clear();
        movieToDelete.getAssocGenres().forEach((genre) -> {
            genre.removeMovie(movieToDelete);
            genreService.save(genre);
        });
        movieToDelete.getAssocGenres().clear();
        movieRepository.save(movieToDelete);
        movieRepository.deleteById(movieId);
    }

    public void addCharacterToMovie(Integer movieId, Integer characterId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        Character character = characterService.findById(characterId);
        movie.addCharacter(character);
        movieRepository.save(movie);
        characterService.save(character);
    }

    public void removeCharacterFromMovie(Integer movieId, Integer characterId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        Character character = characterService.findById(characterId);
        movie.removeCharacter(character);
        movieRepository.save(movie);
        characterService.save(character);
    }
}