package ar.com.cdmoraleda.alkemychallenge.database.services;

import ar.com.cdmoraleda.alkemychallenge.database.models.Genre;
import ar.com.cdmoraleda.alkemychallenge.database.dto.FoundMovie;
import ar.com.cdmoraleda.alkemychallenge.database.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.database.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.database.models.Character;
import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    IMovieRepository movieRepository;
    @Autowired
    CharacterService characterService;
    @Autowired
    GenreService genreService;

    public Movie createMovie(MovieDto movieDto) {
        Movie savedMovie = movieRepository.save(new Movie(movieDto));
        movieDto.getCharacters().forEach((characterDto) -> {
            characterService.createCharacter(characterDto, savedMovie);
        });
        return movieRepository.save(savedMovie);
    }

    public FoundMovie findMovieByTitle(String name) {
        Movie movie = movieRepository.findByTitle(name).orElseThrow();

        return new FoundMovie(movie);
    }

    public List<FoundMovie> filterMoviesByGenre(Integer genreId, String order) {
        List<Movie> fetchedMovies = new ArrayList<>();
        Genre genre = genreService.genreRepository.findById(genreId).orElseThrow();
        //ToDo Cambiar por un if
        switch (order) {
            case "ASC":
                fetchedMovies = movieRepository.findByAssocGenresOrderByReleaseYearAsc(genre);
                break;
            case "DESC":
                fetchedMovies = movieRepository.findByAssocGenresOrderByReleaseYearDesc(genre);
        }

        return fetchedMovies.stream().map(FoundMovie::new).collect(Collectors.toList());
    }

    public Movie updateMovie(MovieDto movieDto, Integer movieId) {
        Movie movieToUpdate = movieRepository.findById(movieId).orElseThrow();
        Movie updatedMovie = new Movie(movieDto, movieToUpdate);

        return movieRepository.save(updatedMovie);
    }

    public void deleteMovie(Integer movieId) {
        Movie movieToDelete = movieRepository.findById(movieId).orElseThrow();
        movieToDelete.getAssocCharacters().forEach((character) -> {
            character.removeMovie(movieToDelete);
            characterService.characterRepository.save(character);
        });
        movieToDelete.getAssocCharacters().clear();
        movieToDelete.getAssocGenres().forEach((genre) -> {
            genre.removeMovie(movieToDelete);
            genreService.genreRepository.save(genre);
        });
        movieToDelete.getAssocGenres().clear();
        movieRepository.save(movieToDelete);
        movieRepository.deleteById(movieId);
    }

    public void addCharacterToMovie(Integer movieId, Integer characterId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        Character character = characterService.characterRepository.findById(characterId).orElseThrow();
        movie.addCharacter(character);
        movieRepository.save(movie);
        characterService.characterRepository.save(character);
    }

    public void removeCharacterFromMovie(Integer movieId, Integer characterId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        Character character = characterService.characterRepository.findById(characterId).orElseThrow();
        movie.removeCharacter(character);
        movieRepository.save(movie);
        characterService.characterRepository.save(character);
    }
}