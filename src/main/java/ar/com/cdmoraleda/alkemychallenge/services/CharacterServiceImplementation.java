package ar.com.cdmoraleda.alkemychallenge.services;


import ar.com.cdmoraleda.alkemychallenge.Repositories.IMovieRepository;
import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.Repositories.ICharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.cdmoraleda.alkemychallenge.dto.CharacterDto;
import org.springframework.stereotype.Service;

import java.util.Set;
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

    public Set<CharacterDto> findBy(String name, Integer age, String weight, Integer movieId) {
        if (name != null && !name.isBlank()) return toDto(characterRepository.findByNameContaining(name));
        else if (weight != null && !weight.isBlank()) return toDto(characterRepository.findByWeight(weight));
        else if (movieId != null) {
            Movie movie = movieRepository.findById(movieId).get();
            return toDto(characterRepository.findByAssocMovies(movie));
        } else if (age != null) return toDto(characterRepository.findByAge(age));
        else return toDto((Set<Character>) characterRepository.findAll());
    }

    public CharacterDto updateCharacter(CharacterDto characterDto, Integer characterId) {
        Character updatedCharacter = new Character(characterDto, characterRepository.findById(characterId).orElseThrow());

        return new CharacterDto(characterRepository.save(updatedCharacter), true);
    }

    public void deleteCharacter(Integer characterId) {
        Character characterToDelete = characterRepository.findById(characterId).orElseThrow();
        for (int i = characterToDelete.getAssocMovies().size(); i > 0; i--){
            Movie movie = characterToDelete.getAssocMovies().iterator().next();
            movie.removeCharacter(characterToDelete);
            movieRepository.save(movie);
        }
            characterToDelete.getAssocMovies().clear();
        characterRepository.deleteById(characterId);
    }

    public Character save(Character character) {
        return characterRepository.save(character);
    }

    public Character findById(Integer characterId) {
        return characterRepository.findById(characterId).orElseThrow();
    }

    public Set<CharacterDto> toDto(Set<Character> characters) {
        return characters.stream().map(CharacterDto::new).collect(Collectors.toSet());
    }
}
