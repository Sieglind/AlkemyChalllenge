package ar.com.cdmoraleda.alkemychallenge.database.controllers;

import ar.com.cdmoraleda.alkemychallenge.database.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.database.dto.CharacterDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    CharacterService characterService;

    @PostMapping
    ResponseEntity<CharacterDto> createCharacter(@RequestBody CharacterDto characterDto) {
        return new ResponseEntity<>(characterService.createCharacter(characterDto), HttpStatus.CREATED);
    }

    @GetMapping(params = {"name"})
    ResponseEntity<List<CharacterDto>> findByName(@RequestParam String name) {
        return new ResponseEntity<>(characterService.findByName(name),HttpStatus.FOUND);
    }

    @GetMapping(params = {"age"})
    ResponseEntity<List<CharacterDto>> filterByAge(@RequestParam Integer age) {
        return new ResponseEntity<>(characterService.filterByAge(age),HttpStatus.FOUND);
    }

    @GetMapping(params = {"weight"})
    ResponseEntity<List<CharacterDto>> filterByWeight(@RequestParam String weight) {
        return new ResponseEntity<>(characterService.filterByWeight(weight),HttpStatus.FOUND);

    }

    @GetMapping(params = {"movieId"})
    ResponseEntity<List<CharacterDto>> filterByMovie(@RequestParam Integer movieId) {
        return new ResponseEntity<>(characterService.filterByMovie(movieId),HttpStatus.FOUND);
    }

    @PutMapping
    ResponseEntity<CharacterDto> updateCharacter(@RequestBody CharacterDto characterDto, @RequestParam Integer characterId){
        return new ResponseEntity<>(characterService.updateCharacter(characterDto,characterId),HttpStatus.OK);
    }

    @DeleteMapping
    ResponseEntity<String> deleteCharacter(@RequestParam Integer characterId) {
        characterService.deleteCharacter(characterId);
        return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
    }
}