package ar.com.cdmoraleda.alkemychallenge.database.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private String pictUrl;
    private String title;
    private Integer releaseYear;
    private Integer score;
    private List<CharacterDto> characters;
}