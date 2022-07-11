package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.GenreDto;
import ar.com.cdmoraleda.alkemychallenge.models.Genre;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.repositories.IGenreRepository;
import ar.com.cdmoraleda.alkemychallenge.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GenreService {

    @Autowired
    IGenreRepository genreRepository;
    @Autowired
    IMovieRepository movieRepository;

    public Genre createGenre(GenreDto genreDto) {
        Genre createdGenre = genreRepository.save(new Genre(genreDto));
        genreDto.getAsoccMovies().forEach((movieId) ->{
            Movie movie = movieRepository.findById(movieId).orElseThrow();
            movie.addGenre(createdGenre);
            movieRepository.save(movie);
        });
        return genreRepository.save(createdGenre);
    }
}