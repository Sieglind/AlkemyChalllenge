package ar.com.cdmoraleda.alkemychallenge.controllers;

import ar.com.cdmoraleda.alkemychallenge.services.CharacterService;

import ar.com.cdmoraleda.alkemychallenge.utilities.OnCreateCharacter;
import ar.com.cdmoraleda.alkemychallenge.utilities.OnUpdateCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.dto.CharacterDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    CharacterService characterService;

    @PostMapping
    @Validated(OnCreateCharacter.class)
    ResponseEntity<CharacterDto> createCharacter(@Valid @RequestBody CharacterDto characterDto) {
        return new ResponseEntity<>(characterService.createCharacter(characterDto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<CharacterDto>> findBy(@RequestParam(required = false) String name,
                                             @RequestParam(required = false) Integer age,
                                             @RequestParam(required = false) String weight,
                                             @RequestParam(required = false) Integer movieId){
        return new ResponseEntity<>(characterService.findBy(name,age,weight,movieId),HttpStatus.OK);
    }

    @PutMapping
    @Validated(OnUpdateCharacter.class)
    ResponseEntity<CharacterDto> updateCharacter(@Valid @RequestBody CharacterDto characterDto, @RequestParam Integer characterId){
        return new ResponseEntity<>(characterService.updateCharacter(characterDto,characterId),HttpStatus.OK);
    }

    @DeleteMapping
    ResponseEntity<String> deleteCharacter(@RequestParam Integer characterId) {
        characterService.deleteCharacter(characterId);
        return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
    }
}