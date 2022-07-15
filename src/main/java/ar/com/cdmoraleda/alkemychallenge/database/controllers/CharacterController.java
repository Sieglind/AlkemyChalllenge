package ar.com.cdmoraleda.alkemychallenge.database.controllers;

import ar.com.cdmoraleda.alkemychallenge.database.dto.FoundCharacter;
import ar.com.cdmoraleda.alkemychallenge.database.models.Character;
import ar.com.cdmoraleda.alkemychallenge.database.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.database.dto.CharacterDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    CharacterService characterService;

    @PostMapping
    Character createCharacter(@RequestBody CharacterDto characterDto) {
        return characterService.createCharacter(characterDto);
    }

    @GetMapping(params = {"name"})
    FoundCharacter findByName(@RequestParam String name) {
        return characterService.findByName(name);
    }

    @GetMapping(params = {"age"})
    List<FoundCharacter> filterByAge(@RequestParam Integer age) {
        return characterService.filterByAge(age);
    }

    @GetMapping(params = {"weight"})
    List<FoundCharacter> filterByWeight(@RequestParam String weight) {
        return characterService.filterByWeight(weight);
    }

    @GetMapping(params = {"movieId"})
    List<FoundCharacter> filterByMovie(@RequestParam Integer movieId) {
        return characterService.filterByMovie(movieId);
    }

    @DeleteMapping
    void deleteCharacter(@RequestParam Integer characterId) {
        characterService.deleteCharacter(characterId);
    }
}