package ar.com.cdmoraleda.alkemychallenge.database.services;

import ar.com.cdmoraleda.alkemychallenge.database.models.Genre;
import ar.com.cdmoraleda.alkemychallenge.database.repositories.IGenreRepository;
import ar.com.cdmoraleda.alkemychallenge.database.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.database.dto.GenreDto;
import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import org.springframework.stereotype.Service;


@Service
public class GenreService {

    @Autowired
    IGenreRepository genreRepository;

    //Todo cambira por Movie Service
    @Autowired
    IMovieRepository movieRepository;

    public Genre createGenre(GenreDto genreDto) {
        Genre createdGenre = genreRepository.save(new Genre(genreDto));
        genreDto.getAssocMovies().forEach((movieId) -> {
            Movie movie = movieRepository.findById(movieId).orElseThrow();
            movie.addGenre(createdGenre);
            movieRepository.save(movie);
        });

        return genreRepository.save(createdGenre);
    }
}