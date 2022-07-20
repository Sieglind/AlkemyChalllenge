package ar.com.cdmoraleda.alkemychallenge.database.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ar.com.cdmoraleda.alkemychallenge.database.dto.CharacterDto;

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
    @ManyToMany(mappedBy = "assocCharacters")
    @JsonIgnoreProperties({"assocCharacters", "assocGenres"})
    private List<Movie> assocMovies;

    public Character(CharacterDto characterDto) {
        this.name = characterDto.getName();
        this.pictUrl = characterDto.getPictUrl();
        this.age = characterDto.getAge();
        this.weight = characterDto.getWeight();
        this.history = characterDto.getHistory();
        this.assocMovies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        this.assocMovies.add(movie);
        movie.getAssocCharacters().add(this);
    }

    public void removeMovie(Movie movie) {
        this.assocMovies.remove(movie);
    }
}