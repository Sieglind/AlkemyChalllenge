package ar.com.cdmoraleda.alkemychallenge.models;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String pictUrl;
    private String title;
    private Integer releaseYear;
    private Integer score;
    @OneToMany
    private List<CFtM> assocCharacters;

    public Movie(MovieDto movie) {
        this.pictUrl = movie.getPictUrl();
        this.title = movie.getTitle();
        this.releaseYear = movie.getReleaseYear();
        this.score = movie.getScore();
    }
}