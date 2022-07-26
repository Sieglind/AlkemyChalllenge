package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    MovieDto createMovie(MovieDto movieDto);
    List<MovieDto> findBy(String name, Integer genreId, String order);
    MovieDto updateMovie(MovieDto movieDto, Integer movieId);
    void deleteMovie(Integer movieId);
    void addCharacterToMovie(Integer movieId, Integer characterId);
    void removeCharacterFromMovie(Integer movieId, Integer characterId);
}
