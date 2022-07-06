package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieGenreDto;
import ar.com.cdmoraleda.alkemychallenge.models.Genre;
import ar.com.cdmoraleda.alkemychallenge.repositories.IGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    @Autowired
    IGenreRepository genreRepository;
    @Autowired
    GFtMService gFtMService;

    public Genre createMovieGenre(MovieGenreDto movieGenreDto) {
        gFtMService.addGFtM(movieGenreDto);
        return genreRepository.save(new Genre(movieGenreDto));
    }
}
