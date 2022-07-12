package ar.com.cdmoraleda.alkemychallenge.dto;

import ar.com.cdmoraleda.alkemychallenge.models.Character;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoundCharacter {
    private String pictUrl;
    private String name;

    public FoundCharacter(Character character) {
        this.pictUrl = character.getPictUrl();
        this.name = character.getName();
    }
}