package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private CFtMService cFtMService;

    public Movie createMovie(MovieDto movieDto) {
        cFtMService.addCFtM(movieDto);
        return movieRepository.save(new Movie(movieDto));
    }

    public List<Movie> findMovie(String title) {
        return movieRepository.findByTitle(title);
    }
}