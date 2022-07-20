package ar.com.cdmoraleda.alkemychallenge.database.models;

import ar.com.cdmoraleda.alkemychallenge.database.dto.MovieDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

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
    @JsonIgnoreProperties("assocMovies")
    private List<Character> assocCharacters;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonIgnoreProperties("assocMovies")
    private List<Genre> assocGenres;

    public Movie(MovieDto movieDto) {
        this.pictUrl = movieDto.getPictUrl();
        this.title = movieDto.getTitle();
        this.releaseYear = movieDto.getReleaseYear();
        this.score = movieDto.getScore();
        this.assocCharacters = new ArrayList<>();
        this.assocGenres = new ArrayList<>();
    }

    public Movie(MovieDto movieDto, Movie movieToUpdate) {
        this.movieId = movieToUpdate.getMovieId();
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
        this.assocCharacters = movieToUpdate.getAssocCharacters();
    }

    public void addGenre(Genre createdGenre) {
        this.assocGenres.add(createdGenre);
        createdGenre.getAssocMovies().add(this);
    }

    public void addCharacter(Character character) {
        this.assocCharacters.add(character);
        character.getAssocMovies().add(this);
    }

    public void removeCharacter(Character character) {
        this.assocCharacters.remove(character);
        character.removeMovie(this);
    }
}