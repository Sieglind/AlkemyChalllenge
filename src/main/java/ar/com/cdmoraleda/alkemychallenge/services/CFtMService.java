package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieCharacterDto;
import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.models.CFtM;
import ar.com.cdmoraleda.alkemychallenge.repositories.ICFtMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CFtMService {
    @Autowired
    private ICFtMRepository cFtMRepository;

    public void addCFtM(MovieDto movieDto) {
        movieDto.getAsoccCharacters().forEach((character) -> {
            if (cFtMRepository.findByCharacterNameAndAndMovieName(character, movieDto.getTitle()).isEmpty()) {
                CFtM charactersFtMovies = new CFtM(character, movieDto.getTitle());
                cFtMRepository.save(charactersFtMovies);
            }
        });
    }

    public void addCFtM(MovieCharacterDto movieCharacterDto) {
        movieCharacterDto.getAsoccMovies().forEach((movie) -> {
            if (cFtMRepository.findByCharacterNameAndAndMovieName(movieCharacterDto.getName(), movie).isEmpty()) {
                CFtM charactersFtMovies = new CFtM(movieCharacterDto.getName(), movie);
                cFtMRepository.save(charactersFtMovies);
            }
        });
    }

    public void deleteCFtM(String characterName) {
        cFtMRepository.deleteByCharacterName(characterName);
    }

}
