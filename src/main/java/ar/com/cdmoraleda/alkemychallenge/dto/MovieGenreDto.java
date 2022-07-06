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
public class MovieGenreDto {
    private String pictUrl;
    private String name;
    private List<String> asoccMovies;
}
