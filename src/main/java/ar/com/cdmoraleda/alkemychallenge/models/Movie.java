package ar.com.cdmoraleda.alkemychallenge.models;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movies_characters",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    @JsonIgnoreProperties("asoccMovies")
    private List<Character> asoccCharacters;

    @ManyToMany(cascade = CascadeType.ALL )
    @JoinTable(
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonIgnoreProperties("asoccMovies")
    private List<Genre> asoccGenres;

    public Movie(MovieDto movieDto) {
        this.pictUrl = movieDto.getPictUrl();
        this.title = movieDto.getTitle();
        this.releaseYear = movieDto.getReleaseYear();
        this.score = movieDto.getScore();
        this.asoccCharacters = new ArrayList<>();
        this.asoccGenres = new ArrayList<>();
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

    public void addGenre(Genre createdGenre) {
        this.asoccGenres.add(createdGenre);
        createdGenre.getAsoccMovies().add(this);
    }

    public void addCharacter(Character character) {
        this.asoccCharacters.add(character);
        character.getAsoccMovies().add(this);
    }

    public void removeCharacter(Character character) {
        this.asoccCharacters.remove(character);
        character.removeMovie(this);
    }
}