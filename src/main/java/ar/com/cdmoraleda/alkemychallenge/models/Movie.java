package ar.com.cdmoraleda.alkemychallenge.models;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Set<Character> assocCharacters;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonIgnoreProperties("asoccMovies")
    private Set<Genre> assocGenres;

    public Movie(MovieDto movieDto) {
        this.pictUrl = movieDto.getPictUrl();
        this.title = movieDto.getTitle();
        this.releaseYear = movieDto.getReleaseYear();
        this.score = movieDto.getScore();
        this.assocCharacters = new HashSet<>();
        this.assocGenres = new HashSet<>();
    }

    public Movie(MovieDto movieDto, Movie movieToUpdate) {
        this.movieId = movieToUpdate.getMovieId();
        this.pictUrl = movieDto.getPictUrl();
        this.title = movieDto.getTitle();
        this.releaseYear = movieDto.getReleaseYear();
        this.score = movieDto.getScore();
        this.assocCharacters = movieToUpdate.getAssocCharacters();
    }

    public void addCharacter(Character character) {
        this.assocCharacters.add(character);
        character.getAssocMovies().add(this);
    }

    public void removeCharacter(Character character) {
        this.assocCharacters.remove(character);
        character.getAssocMovies().remove(this);
    }

    public void addGenre(Genre createdGenre) {
        this.assocGenres.add(createdGenre);
        createdGenre.getAssocMovies().add(this);
    }
}