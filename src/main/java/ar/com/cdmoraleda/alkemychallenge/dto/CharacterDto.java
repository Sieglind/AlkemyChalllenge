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
public class CharacterDto {
    private String pictUrl;
    private String name;
    private Integer age;
    private String weight;
    private String history;
    private List<Integer> asoccMovies;
}
