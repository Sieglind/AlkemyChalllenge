package ar.com.cdmoraleda.alkemychallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private String pictUrl;
    private String title;
    private Integer releaseYear;
    private Integer score;
    private List<CharacterDto> characters;
}