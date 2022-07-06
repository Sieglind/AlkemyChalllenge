package ar.com.cdmoraleda.alkemychallenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "C_FT_M")
public class CFtM {
    @Id
    @GeneratedValue()
    private Integer Id;
    private String characterName;
    private String movieName;

    public CFtM(String character, String title) {
        this.characterName = character;
        this.movieName = title;
    }
}
