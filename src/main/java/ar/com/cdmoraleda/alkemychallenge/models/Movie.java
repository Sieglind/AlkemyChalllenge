package ar.com.cdmoraleda.alkemychallenge.models;

import lombok.*;

import javax.persistence.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table
public class Movie {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String pictUrl;
    @Column
    private String title;
    @Column
    private Integer launchYear;
    @Column
    private Integer score;
    @Column
    private String assocCharacters;
}
