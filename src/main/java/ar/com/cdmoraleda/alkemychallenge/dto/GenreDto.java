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
public class GenreDto {
    private Integer genreId;
    private String pictUrl;
    private String genreName;
    private List<Integer> asoccMovies;
}
