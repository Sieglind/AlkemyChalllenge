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
@Table
public class Character {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private String pictUrl;
    @Column
    private Integer age;
    @Column
    private String weight;
    @Column
    private String history;
    @Column
    private String assocMovies;
}
