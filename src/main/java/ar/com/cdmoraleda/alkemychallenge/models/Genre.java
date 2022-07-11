package ar.com.cdmoraleda.alkemychallenge.models;

import ar.com.cdmoraleda.alkemychallenge.dto.GenreDto;
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
@Table(name = "GENRES")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer genreId;
    private String pictUrl;
    private String genreName;

    @ManyToMany( mappedBy = "asoccGenres" )
    @JsonIgnoreProperties({"asoccCharacters","asoccGenres"})
    private List<Movie> asoccMovies;

    public Genre(GenreDto genreDto) {
        this.pictUrl = genreDto.getPictUrl();
        this.genreName = genreDto.getGenreName();
        this.asoccMovies = new ArrayList<>();
    }

    public void removeMovie(Movie movieToDelete) {
        this.asoccMovies.remove(movieToDelete);
    }
}