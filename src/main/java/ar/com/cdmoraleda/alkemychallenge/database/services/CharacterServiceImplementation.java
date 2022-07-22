package ar.com.cdmoraleda.alkemychallenge.database.services;


import ar.com.cdmoraleda.alkemychallenge.database.Repositories.IMovieRepository;
import ar.com.cdmoraleda.alkemychallenge.database.models.Character;
import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.database.Repositories.ICharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.database.dto.CharacterDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImplementation implements CharacterService {
    @Autowired
    ICharacterRepository characterRepository;

    @Autowired
    IMovieRepository movieRepository;

    public CharacterDto createCharacter(CharacterDto characterDto) {
        Character savedCharacter = characterRepository.save(new Character(characterDto));
        characterDto.getMovies().forEach((movieId) -> {
            Movie movie = movieRepository.findById(movieId).orElseThrow();
            movie.addCharacter(savedCharacter);
            movieRepository.save(movie);
        });
        return new CharacterDto(characterRepository.save(savedCharacter), true);
    }

    public void createCharacter(CharacterDto characterDto, Movie movie) {
        Character savedCharacter;
        if (characterRepository.findByName(characterDto.getName()).isPresent()) {
            savedCharacter = characterRepository.findByName(characterDto.getName()).get();
        } else {
            savedCharacter = new Character(characterDto);
        }
        savedCharacter.addMovie(movie);
        characterRepository.save(savedCharacter);
    }

    public List<CharacterDto> findByName(String name) {
        Optional<Character> characters = characterRepository.findByNameContaining(name);
        if (characters.isPresent()) {
            return characters.stream().map(CharacterDto::new).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public List<CharacterDto> filterByAge(Integer age) {
        List<Character> characters = characterRepository.findByAge(age);
        if (!characters.isEmpty()) {
            return characters.stream().map(CharacterDto::new).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public List<CharacterDto> filterByWeight(String weight) {
        List<Character> characters = characterRepository.findByWeight(weight);
        if (!characters.isEmpty()) {
            return characters.stream().map(CharacterDto::new).collect(Collectors.toList());
        } else return null;
    }

    public List<CharacterDto> filterByMovie(Integer movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()) {
            List<Character> characters = characterRepository.findByAssocMovies(movie.get());
            if (!characters.isEmpty()) {
                return characters.stream().map(CharacterDto::new).collect(Collectors.toList());
            } else return null;
        } else return null;
    }

    public CharacterDto updateCharacter(CharacterDto characterDto, Integer characterId) {
        Character updatedCharacter = new Character(characterDto, characterRepository.findById(characterId).orElseThrow());
        return new CharacterDto(characterRepository.save(updatedCharacter), true);
    }

    public void deleteCharacter(Integer characterId) {
        Character characterToDelete = characterRepository.findById(characterId).orElseThrow();
        characterToDelete.getAssocMovies().forEach((movie) -> {
            movie.getAssocCharacters().remove(characterToDelete);
            movieRepository.save(movie);
        });
        characterToDelete.getAssocMovies().clear();
        characterRepository.deleteById(characterId);
    }

    public Character save(Character character) {
        return characterRepository.save(character);
    }

    public Character findById(Integer characterId) {
        return characterRepository.findById(characterId).orElseThrow();
    }
}
