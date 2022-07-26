package ar.com.cdmoraleda.alkemychallenge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ar.com.cdmoraleda.alkemychallenge.dto.GenreDto;

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
@Table(name = "GENRES")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId;
    private String pictUrl;
    private String genreName;

    @ManyToMany(mappedBy = "assocGenres")
    @JsonIgnoreProperties({"assocCharacters", "assocGenres"})
    private List<Movie> assocMovies;

    public Genre(GenreDto genreDto) {
        this.pictUrl = genreDto.getPictUrl();
        this.genreName = genreDto.getGenreName();
        this.assocMovies = new ArrayList<>();
    }

    public void removeMovie(Movie movieToDelete) {
        this.assocMovies.remove(movieToDelete);
    }
}