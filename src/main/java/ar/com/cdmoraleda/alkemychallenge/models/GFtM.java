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
@Table(name = "G_FT_M")
public class GFtM {
    @Id
    @GeneratedValue()
    private Integer Id;
    private String genreName;
    private String movieName;

    public GFtM(String character, String title) {
        this.genreName = character;
        this.movieName = title;
    }
}