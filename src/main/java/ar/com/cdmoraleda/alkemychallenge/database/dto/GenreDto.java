package ar.com.cdmoraleda.alkemychallenge.database.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreDto {
    private Integer genreId;
    private String pictUrl;
    private String genreName;
    private List<Integer> assocMovies;
}
