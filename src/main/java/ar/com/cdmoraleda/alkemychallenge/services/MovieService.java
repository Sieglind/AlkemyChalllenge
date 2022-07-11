package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.FoundMovie;
import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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
            characterService.createCharacter(characterDto,savedMovie);
        });
        return movieRepository.save(savedMovie);
    }

    public FoundMovie findMovieByTitle(String name) {
        Movie movie = movieRepository.findByTitle(name);
        return new FoundMovie(movie);
    }

    public List<FoundMovie> filterMoviesByGenre(Integer genreId, String order) {
        List<FoundMovie> foundMovies = new ArrayList<>();
        List<Movie> fetchedMovies = new ArrayList<>();
        switch (order){
            case "ASC":
                fetchedMovies = movieRepository.findByAsoccGenresOrderByReleaseYearAsc(genreService.genreRepository.findById(genreId).orElseThrow());
                break;
            case "DESC":
                fetchedMovies = movieRepository.findByAsoccGenresOrderByReleaseYearDesc(genreService.genreRepository.findById(genreId).orElseThrow());
        }
        fetchedMovies.forEach( (fetchedMovie) ->{
            foundMovies.add(new FoundMovie(fetchedMovie));
        });
        return foundMovies;
    }

    public Movie updateMovie(MovieDto movieDto, Integer movieId) {
        Movie movieToUpdate = movieRepository.findById(movieId).orElseThrow();
        Movie updatedMovie = new Movie(movieDto, movieToUpdate);
        return movieRepository.save(updatedMovie);
    }

    public void deleteMovie(Integer movieId) {
        Movie movieToDelete = movieRepository.findById(movieId).orElseThrow();
        movieToDelete.getAsoccCharacters().forEach((character) -> {
            character.removeMovie(movieToDelete);
            characterService.characterRepository.save(character);
        });
        movieToDelete.getAsoccCharacters().clear();
        movieToDelete.getAsoccGenres().forEach((genre) -> {
            genre.removeMovie(movieToDelete);
            genreService.genreRepository.save(genre);
        });
        movieToDelete.getAsoccGenres().clear();
        movieRepository.save(movieToDelete);
        movieRepository.deleteById(movieId);
    }

    public void addCharacterToMovie(Integer movieId, Integer characterId){
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