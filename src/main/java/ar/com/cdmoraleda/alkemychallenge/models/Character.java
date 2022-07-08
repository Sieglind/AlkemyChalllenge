package ar.com.cdmoraleda.alkemychallenge.models;

import ar.com.cdmoraleda.alkemychallenge.dto.CharacterDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
    @JsonIgnoreProperties("asoccCharacters")
    @ManyToMany(mappedBy = "asoccCharacters")
    private List<Movie> asoccMovies;

    public Character(CharacterDto characterDto) {
        this.name = characterDto.getName();
        this.pictUrl = characterDto.getPictUrl();
        this.age = characterDto.getAge();
        this.weight = characterDto.getWeight();
        this.history = characterDto.getHistory();
    }
}