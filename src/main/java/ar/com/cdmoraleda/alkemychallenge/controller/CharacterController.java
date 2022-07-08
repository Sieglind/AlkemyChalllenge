package ar.com.cdmoraleda.alkemychallenge.controller;

import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.services.CharacterService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    CharacterService characterService;

    @GetMapping
    Character findByName(@RequestParam String name) {
        return characterService.findByName(name);
    }
}