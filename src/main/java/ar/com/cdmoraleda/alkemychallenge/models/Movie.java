package ar.com.cdmoraleda.alkemychallenge.models;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "MOVIES")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer movieId;
    private String pictUrl;
    private String title;
    private Integer releaseYear;
    private Integer score;
    @JsonIgnoreProperties("assocMovies")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movies_characters",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private List<Character> asoccCharacters;

    public Movie(MovieDto movieDto) {
        this.pictUrl = movieDto.getPictUrl();
        this.title = movieDto.getTitle();
        this.releaseYear = movieDto.getReleaseYear();
        this.score = movieDto.getScore();
        this.asoccCharacters = new ArrayList<>();
    }

    public Movie(MovieDto movieDto, Movie movieToUpdate) {
        this.movieId=movieToUpdate.getMovieId();
        if (movieDto.getPictUrl() != null) {
            this.pictUrl = movieDto.getPictUrl();
        }
        if (movieDto.getTitle() != null) {
            this.title = movieDto.getTitle();
        }
        if (movieDto.getReleaseYear() != null) {
            this.releaseYear = movieDto.getReleaseYear();
        }
        if (movieDto.getScore() != null) {
            this.score = movieDto.getScore();
        }
        this.asoccCharacters = movieToUpdate.getAsoccCharacters();
    }
}