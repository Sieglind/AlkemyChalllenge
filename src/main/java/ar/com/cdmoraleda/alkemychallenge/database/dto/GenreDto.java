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
public class GenreDto {
    private Integer genreId;
    private String pictUrl;
    private String genreName;
    private List<Integer> assocMovies;
}
