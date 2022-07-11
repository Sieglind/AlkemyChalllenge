package ar.com.cdmoraleda.alkemychallenge.models;

import ar.com.cdmoraleda.alkemychallenge.dto.CharacterDto;
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
@Table(name = "CHARACTERS")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer characterId;
    private String name;
    private String pictUrl;
    private Integer age;
    private String weight;
    private String history;
    @ManyToMany(mappedBy = "asoccCharacters")
    @JsonIgnoreProperties({"asoccCharacters","asoccGenres"})
    private List<Movie> asoccMovies;

    public Character(CharacterDto characterDto) {
        this.name = characterDto.getName();
        this.pictUrl = characterDto.getPictUrl();
        this.age = characterDto.getAge();
        this.weight = characterDto.getWeight();
        this.history = characterDto.getHistory();
        this.asoccMovies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        this.asoccMovies.add(movie);
        movie.getAsoccCharacters().add(this);
    }

    public void removeMovie(Movie movie) {
        this.asoccMovies.remove(movie);
    }
}