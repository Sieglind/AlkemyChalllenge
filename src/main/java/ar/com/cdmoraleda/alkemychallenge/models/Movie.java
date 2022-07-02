package ar.com.cdmoraleda.alkemychallenge.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Movies")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String picture;
    private String title;
    private Integer year;
    private Integer score;
    private Integer asoccCharacters;
}
