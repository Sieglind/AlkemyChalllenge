package ar.com.cdmoraleda.alkemychallenge.database.services;

import ar.com.cdmoraleda.alkemychallenge.database.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.database.Repositories.IMovieRepository;
import ar.com.cdmoraleda.alkemychallenge.database.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.database.models.Character;
import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
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

    public List<MovieDto> findMovieByTitle(String name,String order) {
        if (order == "ASC"){
            return movieRepository.findMovieByTitleContainingOrderByReleaseYearAsc(name).stream()
                    .map(MovieDto::new).collect(Collectors.toList());
        } else {
            return movieRepository.findMovieByTitleContainingOrderByReleaseYearDesc(name).stream()
                    .map(MovieDto::new).collect(Collectors.toList());
        }
    }

    public List<MovieDto> filterMoviesByGenre(Integer genreId, String order) {
        Genre genre = genreService.findById(genreId);
        if (order == "ASC"){
            return movieRepository.findByAssocGenresOrderByReleaseYearAsc(genre).stream()
                    .map(MovieDto::new).collect(Collectors.toList());
        } else {
            System.out.println("Entre al Descendente");
            return movieRepository.findByAssocGenresOrderByReleaseYearDesc(genre).stream()
                    .map(MovieDto::new).collect(Collectors.toList());
        }
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

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie findById(Integer movieId) {
        return movieRepository.findById(movieId).orElseThrow();
    }
}