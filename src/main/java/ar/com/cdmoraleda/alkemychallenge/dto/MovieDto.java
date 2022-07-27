package ar.com.cdmoraleda.alkemychallenge.dto;

import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import ar.com.cdmoraleda.alkemychallenge.utilities.OnCreateMovie;
import ar.com.cdmoraleda.alkemychallenge.utilities.OnUpdateMovie;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDto {
    private Integer movieId;

    @NotBlank(message = "Must provide a link from IMBd")
    @URL(host = "www.imdb.com", message = "Must provide a link from IMBd")
    private String pictUrl;

    @NotBlank(message = "Must provide a title")
    private String title;

    @NotNull
    @Min(value = 1937, message = "Release year must be after than 1936")
    @Max(value = 2050, message = "Release year must be before 2050")
    private Integer releaseYear;

    @NotNull(message = "Must not be null")
    @Min(value = 0, message = "Score must be positive")
    @Max(value = 10, message = "Score must be lower than 10")
    private Integer score;

    @NotEmpty(groups = OnCreateMovie.class, message = "Please add at least one character to the movie")
    @Null(groups = OnUpdateMovie.class, message = "Associated characters shall not be updated through this method")
    private Set<@Valid CharacterDto> assocCharacters;

    public MovieDto(Movie movie, Boolean addAssociatedCharacters) {
        this.movieId = movie.getMovieId();
        this.pictUrl = movie.getPictUrl();
        this.title = movie.getTitle();
        this.releaseYear = movie.getReleaseYear();
        this.score = movie.getScore();
        if (addAssociatedCharacters) {
            this.assocCharacters = movie.getAssocCharacters().stream()
                    .map(character -> new CharacterDto(character, false))
                    .collect(Collectors.toSet());
        }
    }

    public MovieDto(Movie movie) {
        this.pictUrl = movie.getPictUrl();
        this.title = movie.getTitle();
        this.releaseYear = movie.getReleaseYear();
    }
}