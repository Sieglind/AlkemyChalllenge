package ar.com.cdmoraleda.alkemychallenge.database.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreDto {
    private Integer genreId;
    @NotBlank @URL(host = "www.imdb.com")
    private String pictUrl;
    private String genreName;
    private List<Integer> assocMovies;
}
