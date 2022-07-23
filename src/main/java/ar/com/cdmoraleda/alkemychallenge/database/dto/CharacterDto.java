package ar.com.cdmoraleda.alkemychallenge.database.dto;

import ar.com.cdmoraleda.alkemychallenge.database.models.Character;

import ar.com.cdmoraleda.alkemychallenge.database.utilities.OnCreateCharacter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterDto {
    private Integer characterId;
    @NotBlank @URL(host = "www.imdb.com")
    private String pictUrl;
    @NotBlank
    private String name;
    @NotNull @Min(0) @Max(100000000)
    private Integer age;
    @NotBlank
    private String weight;
    @NotBlank
    private String history;
    @NotEmpty(groups = OnCreateCharacter.class)
    private List<@NotNull Integer> movies;
    private List<MovieDto> assocMovies;
    public CharacterDto(Character character,Boolean addAssociatedMovies) {
        this.characterId = character.getCharacterId();
        this.pictUrl = character.getPictUrl();
        this.name = character.getName();
        this.age = character.getAge();
        this.weight = character.getWeight();
        this.history = character.getHistory();
        if (addAssociatedMovies) {
            this.assocMovies = character.getAssocMovies().stream()
                    .map(movie -> new MovieDto(movie, false))
                    .collect(Collectors.toList());
        }
    }

    public CharacterDto(Character character) {
        this.pictUrl=character.getPictUrl();
        this.name= character.getName();
    }
}
