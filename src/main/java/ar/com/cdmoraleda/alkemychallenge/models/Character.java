package ar.com.cdmoraleda.alkemychallenge.models;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieCharacterDto;
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
@Table(name = "Characters")
public class Character {
    @Id
    @GeneratedValue()
    private Integer id;
    private String name;
    private String pictUrl;
    private Integer age;
    private String weight;
    private String history;
    @OneToMany
    private List<CFtM> assocMovies;

    public Character(MovieCharacterDto movieCharacterDto) {
        this.name = movieCharacterDto.getName();
        this.pictUrl = movieCharacterDto.getPictUrl();
        this.age = movieCharacterDto.getAge();
        this.weight = movieCharacterDto.getWeight();
        this.history = movieCharacterDto.getHistory();
    }

    public Character(Integer id, MovieCharacterDto movieCharacterDto) {
        new Character(movieCharacterDto);
        this.id = id;
    }
}
