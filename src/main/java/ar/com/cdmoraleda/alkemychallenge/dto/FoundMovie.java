package ar.com.cdmoraleda.alkemychallenge.dto;

import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
