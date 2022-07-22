package ar.com.cdmoraleda.alkemychallenge.database.dto;

import ar.com.cdmoraleda.alkemychallenge.database.models.Character;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterDto {
    private Integer characterId;
    private String pictUrl;
    private String name;
    private Integer age;
    private String weight;
    private String history;
    private List<Integer> movies;
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
