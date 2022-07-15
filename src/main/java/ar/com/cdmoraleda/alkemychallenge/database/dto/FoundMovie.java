package ar.com.cdmoraleda.alkemychallenge.database.dto;

import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoundMovie {
    private String pictUrl;
    private String title;
    private Integer releaseYear;

    public FoundMovie(Movie movie){
        this.pictUrl= movie.getPictUrl();
        this.title=movie.getTitle();
        this.releaseYear= movie.getReleaseYear();
    }
}