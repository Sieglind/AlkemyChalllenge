package ar.com.cdmoraleda.alkemychallenge.dto;

import ar.com.cdmoraleda.alkemychallenge.models.Character;


import ar.com.cdmoraleda.alkemychallenge.utilities.OnCreateCharacter;
import ar.com.cdmoraleda.alkemychallenge.utilities.OnUpdateCharacter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterDto {
    private Integer characterId;

    @NotBlank(message = "Must provide a link from IMBd")
    @URL(host = "www.imdb.com", message = "Must provide a link from IMBd")
    private String pictUrl;

    @NotBlank(message = "Must provide a name")
    private String name;

    @NotNull(message = "Must provide an age")
    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 100000000, message = "Age must be lower than 100000000")
    private Integer age;

    @NotBlank(message = "Choose a weight for the character")
    private String weight;

    @NotBlank(message = "Must provide a history for the character")
    private String history;

    @Null(groups = OnUpdateCharacter.class, message = "Associated movies shall not be added through this method")
    @NotEmpty(groups = OnCreateCharacter.class, message = "Please add related movies Id")
    private Set<Integer> movies;

    private Set<MovieDto> assocMovies;

    public CharacterDto(Character character, Boolean addAssociatedMovies) {
        this.characterId = character.getCharacterId();
        this.pictUrl = character.getPictUrl();
        this.name = character.getName();
        this.age = character.getAge();
        this.weight = character.getWeight();
        this.history = character.getHistory();
        if (addAssociatedMovies) {
            this.assocMovies = character.getAssocMovies().stream()
                    .map(movie -> new MovieDto(movie, false))
                    .collect(Collectors.toSet());
        }
    }

    public CharacterDto(Character character) {
        this.pictUrl = character.getPictUrl();
        this.name = character.getName();
    }
}
