package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieGenreDto;
import ar.com.cdmoraleda.alkemychallenge.models.GFtM;
import ar.com.cdmoraleda.alkemychallenge.repositories.IGFtMrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GFtMService {
    @Autowired
    IGFtMrepository gFtMrepository;

    void addGFtM(MovieGenreDto movieGenreDto) {
        movieGenreDto.getAsoccMovies().forEach((genre) -> {
            GFtM gFtM = new GFtM(genre, movieGenreDto.getName());
            gFtMrepository.save(gFtM);
        });
    }
}
