package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.CharacterToShow;
import ar.com.cdmoraleda.alkemychallenge.dto.MovieCharacterDto;
import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.repositories.ICharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CharacterService {
    @Autowired
    private ICharacterRepository characterRepository;
    @Autowired
    private CFtMService cFtMService;

    public Character createMovieCharacter(MovieCharacterDto movieCharacterDto) {
        cFtMService.addCFtM(movieCharacterDto);
        return characterRepository.save(new Character(movieCharacterDto));
    }

    public Character updateMovieCharacter(Integer id, MovieCharacterDto movieCharacterDto) {
        return characterRepository.save(new Character(id, movieCharacterDto));
    }

    public String deleteMovieCharacter(Integer id, MovieCharacterDto movieCharacterDto) {
        characterRepository.deleteById(id);
        cFtMService.deleteCFtM(movieCharacterDto.getName());
        return "The Character has been deleted";
    }

    public List<CharacterToShow> findMovieCharacterByName(String name) {
        return prepareToShow(characterRepository.findByName(name));
    }

    public List<CharacterToShow> findMovieCharacterByAge(Integer age) {
        return prepareToShow(characterRepository.findByAge(age));
    }

    public List<CharacterToShow> findMovieCharacterByWeight(String weight) {
        return prepareToShow(characterRepository.findByWeight(weight));
    }

    private List<CharacterToShow> prepareToShow(List<Character> characters) {
        List<CharacterToShow> charactersToShow = new ArrayList<>();
        characters.forEach((character) -> {
            charactersToShow.add(new CharacterToShow(character));
        });
        return charactersToShow;
    }
}
