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
public class CharacterDto {
    private String pictUrl;
    private String name;
    private Integer age;
    private String weight;
    private String history;
    private List<Integer> movies;
}
