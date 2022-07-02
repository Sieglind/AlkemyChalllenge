package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private IMovieRepository movieRepository;


    public Movie createMovie(MovieDto movie){
        Movie movieToSave = new Movie().builder()
                .picture(movie.getPicture())
                .title(movie.getTitle())
                .year(movie.getYear())
                .score(movie.getScore())
                .build();
        return movieRepository.save(movieToSave);
    }

}
