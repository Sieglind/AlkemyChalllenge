package ar.com.cdmoraleda.alkemychallenge.database.dto;

import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDto {
    private Integer movieId;
    private String pictUrl;
    private String title;
    private Integer releaseYear;
    private Integer score;
    private List<CharacterDto> assocCharacters;

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