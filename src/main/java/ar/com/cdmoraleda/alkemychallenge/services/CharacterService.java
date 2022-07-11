package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.CharacterDto;
import ar.com.cdmoraleda.alkemychallenge.dto.FoundCharacter;
import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.repositories.ICharacterRepository;
import ar.com.cdmoraleda.alkemychallenge.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CharacterService {
    @Autowired
    ICharacterRepository characterRepository;
    @Autowired
    IMovieRepository movieRepository;

    public Character createCharacter(CharacterDto characterDto) {
        Character savedCharacter = characterRepository.save(new Character(characterDto));
        characterDto.getMovies().forEach((movieId) ->{
            Movie movie = movieRepository.findById(movieId).orElseThrow();
            movie.addCharacter(savedCharacter);
            movieRepository.save(movie);
        });
        return characterRepository.save(savedCharacter);
    }

    public Movie createCharacter(CharacterDto characterDto, Movie movie){
        Character savedCharacter;
        if (characterRepository.findByName(characterDto.getName()) == null){
            savedCharacter = new Character(characterDto);
        }else {
            savedCharacter = characterRepository.findByName(characterDto.getName());
        }
        savedCharacter.addMovie(movie);
        characterRepository.save(savedCharacter);
        return movie;
    }

    public FoundCharacter findByName(String name) {
        return new FoundCharacter(characterRepository.findByName(name));
    }

    public List<FoundCharacter> filterByAge(Integer age) {
        List<FoundCharacter> foundCharacters = new ArrayList<>();
        List<Character> fetchedCharacters = characterRepository.findByAge(age);
        fetchedCharacters.forEach((character) ->{
            foundCharacters.add(new FoundCharacter(character));
        });
        return  foundCharacters;
    }

    public List<FoundCharacter> filterByWeight(String weight) {
        List<FoundCharacter> foundCharacters = new ArrayList<>();
        List<Character> fetchedCharacters = characterRepository.findByWeight(weight);
        fetchedCharacters.forEach((character) ->{
            foundCharacters.add(new FoundCharacter(character));
        });
        return foundCharacters;

    }

    public List<FoundCharacter> filterByMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        List<FoundCharacter> foundCharacters = new ArrayList<>();
        List<Character> fetchedCharacters = characterRepository.findByAsoccMovies(movie);
        fetchedCharacters.forEach((character) ->{
            foundCharacters.add(new FoundCharacter(character));
        });
        return foundCharacters;
    }

    public void deleteCharacter(Integer characterId) {
        Character characterToDelete = characterRepository.findById(characterId).orElseThrow();
        characterToDelete.getAsoccMovies().forEach((movie) ->{
            movie.getAsoccCharacters().remove(characterToDelete);
            movieRepository.save(movie);
        });
        characterToDelete.getAsoccMovies().clear();
        characterRepository.deleteById(characterId);
    }
}
