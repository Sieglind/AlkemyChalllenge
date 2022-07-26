package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.GenreDto;
import ar.com.cdmoraleda.alkemychallenge.models.Genre;
import org.springframework.stereotype.Service;

@Service
public interface GenreService {
    Genre createGenre(GenreDto genreDto);
    Genre save(Genre genre);
    Genre findById(Integer genreId);
}
