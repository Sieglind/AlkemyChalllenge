package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.CharacterDto;
import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;

import java.util.List;
import java.util.Set;


public interface CharacterService {
    CharacterDto createCharacter(CharacterDto characterDto);
    void createCharacter(CharacterDto characterDto, Movie movie);
    Set<CharacterDto> findBy(String name, Integer age, String weight, Integer movieId);
    CharacterDto updateCharacter(CharacterDto characterDto, Integer characterId);
    void deleteCharacter(Integer characterId);
    Character save(Character character);
    Character findById(Integer characterId);

}