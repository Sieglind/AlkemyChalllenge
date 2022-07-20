package ar.com.cdmoraleda.alkemychallenge.database.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {


    @NotNull @NotEmpty(message = "You must provide an URL")
    private String pictUrl;
    private String title;
    private Integer releaseYear;
    private Integer score;
    private List<CharacterDto> characters;
}