package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.Repositories.IGenreRepository;
import ar.com.cdmoraleda.alkemychallenge.Repositories.IMovieRepository;
import ar.com.cdmoraleda.alkemychallenge.models.Genre;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.dto.GenreDto;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImplementation implements GenreService {

    @Autowired
    IGenreRepository genreRepository;

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

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre findById(Integer genreId) {
        if (genreId == null) {
            return null;
        } else {
            return genreRepository.findById(genreId).get();
        }
    }
}