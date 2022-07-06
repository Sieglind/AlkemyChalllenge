package ar.com.cdmoraleda.alkemychallenge.dto;

import ar.com.cdmoraleda.alkemychallenge.models.Character;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterToShow {
    private String pictUrl;
    private String name;

    public CharacterToShow(Character character) {
        this.pictUrl = character.getPictUrl();
        this.name = character.getName();
    }
}
