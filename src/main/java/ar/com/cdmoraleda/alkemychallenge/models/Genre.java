package ar.com.cdmoraleda.alkemychallenge.models;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieGenreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String pictUrl;
    private String name;
    @OneToMany
    private List<Movie> assocMovies;

    public Genre(MovieGenreDto movieGenreDto) {
        this.pictUrl = movieGenreDto.getPictUrl();
        this.name = movieGenreDto.getName();
    }
}
