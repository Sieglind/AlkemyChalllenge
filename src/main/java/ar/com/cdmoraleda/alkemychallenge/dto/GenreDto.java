package ar.com.cdmoraleda.alkemychallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenreDto {
    private Integer genreId;
    @NotBlank(message = "Must provide a link from IMBd")
    @URL(host = "www.imdb.com", message = "Must provide a link from IMBd")
    private String pictUrl;
    private String genreName;
    private Set<Integer> assocMovies;
}
