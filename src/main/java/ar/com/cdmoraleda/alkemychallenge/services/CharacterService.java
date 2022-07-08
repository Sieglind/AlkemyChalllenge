package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.CharacterDto;
import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.repositories.ICharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    @Autowired
    ICharacterRepository characterRepository;
    public Character createCharacter(CharacterDto characterDto) {
        Character characterToSave = null;
        if (findByName(characterDto.getName()) == null) {
            characterToSave = new Character(characterDto);
        } else {
            characterToSave = findByName(characterDto.getName());
        }
        return characterRepository.save(characterToSave);
    }

    public Character findByName(String name){
        return characterRepository.findByName(name);
    }
}
