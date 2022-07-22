package ar.com.cdmoraleda.alkemychallenge.database.services;

import ar.com.cdmoraleda.alkemychallenge.database.dto.GenreDto;
import ar.com.cdmoraleda.alkemychallenge.database.models.Genre;
import org.springframework.stereotype.Service;

@Service
public interface GenreService {
    Genre createGenre(GenreDto genreDto);
    Genre save(Genre genre);
    Genre findById(Integer genreId);
}
