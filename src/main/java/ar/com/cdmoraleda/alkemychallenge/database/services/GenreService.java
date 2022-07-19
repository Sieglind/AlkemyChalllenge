package ar.com.cdmoraleda.alkemychallenge.database.services;

import ar.com.cdmoraleda.alkemychallenge.database.models.Genre;
import ar.com.cdmoraleda.alkemychallenge.security.dto.repositories.IGenreRepository;
import ar.com.cdmoraleda.alkemychallenge.security.dto.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.database.dto.GenreDto;
import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import org.springframework.stereotype.Service;


@Service
public class GenreService {

    @Autowired
    IGenreRepository genreRepository;
    @Autowired
    IMovieRepository movieRepository;

    public Genre createGenre(GenreDto genreDto) {
        Genre createdGenre = genreRepository.save(new Genre(genreDto));
        genreDto.getAsoccMovies().forEach((movieId) -> {
            Movie movie = movieRepository.findById(movieId).orElseThrow();
            movie.addGenre(createdGenre);
            movieRepository.save(movie);
        });
        return genreRepository.save(createdGenre);
    }
}