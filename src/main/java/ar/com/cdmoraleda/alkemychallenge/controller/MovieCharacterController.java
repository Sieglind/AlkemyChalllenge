package ar.com.cdmoraleda.alkemychallenge.controller;

import ar.com.cdmoraleda.alkemychallenge.dto.CharacterToShow;
import ar.com.cdmoraleda.alkemychallenge.dto.MovieCharacterDto;
import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class MovieCharacterController {
    @Autowired
    private CharacterService characterService;

    @PostMapping
    Character createCharacter(@RequestBody MovieCharacterDto movieCharacterDto) {
        return characterService.createMovieCharacter(movieCharacterDto);
    }

    @PostMapping("/update")
    Character updateCharacter(@RequestParam Integer id, @RequestBody MovieCharacterDto movieCharacterDto) {
        return characterService.updateMovieCharacter(id, movieCharacterDto);
    }

    @DeleteMapping
    String deleteCharacter(@RequestParam Integer id, @RequestBody MovieCharacterDto movieCharacterDto) {
        return characterService.deleteMovieCharacter(id, movieCharacterDto);
    }

    @GetMapping(params = {"name"})
    List<CharacterToShow> findCharacterByName(@RequestParam String name) {
        return characterService.findMovieCharacterByName(name);
    }

    @GetMapping(params = {"age"})
    List<CharacterToShow> findCharacterByAge(@RequestParam Integer age) {
        return characterService.findMovieCharacterByAge(age);
    }

    @GetMapping(params = {"weight"})
    List<CharacterToShow> findMovieCharacterByWeight(@RequestParam String weight) {
        return characterService.findMovieCharacterByWeight(weight);
    }
}