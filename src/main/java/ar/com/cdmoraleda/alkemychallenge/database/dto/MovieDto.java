package ar.com.cdmoraleda.alkemychallenge.database.dto;

import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.database.utilities.OnCreateMovie;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDto {
    private Integer movieId;
    @NotBlank @URL(host = "www.imdb.com")
    private String pictUrl;
    @NotBlank
    private String title;
    @NotNull @Min(1937) @Max(2050)
    private Integer releaseYear;
    @NotNull @Min(0) @Max(10)
    private Integer score;
    @NotEmpty(groups = OnCreateMovie.class)
    private List<@Valid CharacterDto> assocCharacters;

    public MovieDto(Movie movie,Boolean addAssociatedCharacters) {
        this.movieId=movie.getMovieId();
        this.pictUrl= movie.getPictUrl();
        this.title= movie.getTitle();
        this.releaseYear=movie.getReleaseYear();
        this.score=movie.getScore();
        if (addAssociatedCharacters){
            this.assocCharacters=movie.getAssocCharacters().stream()
                    .map(character -> new CharacterDto(character,false))
                    .collect(Collectors.toList());
        }
    }

    public MovieDto(Movie movie) {
        this.pictUrl=movie.getPictUrl();
        this.title= movie.getTitle();
        this.releaseYear=movie.getReleaseYear();
    }
}