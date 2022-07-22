package ar.com.cdmoraleda.alkemychallenge.database.services;

import ar.com.cdmoraleda.alkemychallenge.database.dto.CharacterDto;
import ar.com.cdmoraleda.alkemychallenge.database.models.Character;
import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;

import java.util.List;


public interface CharacterService {
    CharacterDto createCharacter(CharacterDto characterDto);
    void createCharacter(CharacterDto characterDto, Movie movie);
    List<CharacterDto> findByName(String name);
    List<CharacterDto> filterByAge(Integer age);
    List<CharacterDto> filterByWeight(String weight);
    List<CharacterDto> filterByMovie(Integer movieId);
    CharacterDto updateCharacter(CharacterDto characterDto, Integer characterId);
    void deleteCharacter(Integer characterId);
    Character save(Character character);
    Character findById(Integer characterId);
}
