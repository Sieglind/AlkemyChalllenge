package ar.com.cdmoraleda.alkemychallenge.database.services;

import ar.com.cdmoraleda.alkemychallenge.database.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    MovieDto createMovie(MovieDto movieDto);
    List<MovieDto> findMovieByTitle(String name, String order);
    List<MovieDto>filterMoviesByGenre(Integer genreId,String order);
    MovieDto updateMovie(MovieDto movieDto, Integer movieId);
    void deleteMovie(Integer movieId);
    void addCharacterToMovie(Integer movieId, Integer characterId);
    void removeCharacterFromMovie(Integer movieId, Integer characterId);
    Movie save(Movie movie);
    Movie findById(Integer movieId);
}
