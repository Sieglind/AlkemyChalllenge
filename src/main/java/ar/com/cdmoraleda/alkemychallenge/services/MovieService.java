package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MovieService {
    @Autowired
    IMovieRepository movieRepository;
    @Autowired
    CharacterService characterService;

    public Movie createMovie(MovieDto movieDto) {
        Movie movieToSave = new Movie(movieDto);
        movieDto.getCharacters().forEach((characterDto -> {
            movieToSave.getAsoccCharacters().add(characterService.createCharacter(characterDto));
        }));
        return movieRepository.save(movieToSave);
    }

    public Movie updateMovie(MovieDto movieDto, Integer id) {
        Movie movieToUpdate = movieRepository.findById(id).get();
        Movie updatedMovie = new Movie(movieDto,movieToUpdate);
        return movieRepository.save(updatedMovie);
    }
}