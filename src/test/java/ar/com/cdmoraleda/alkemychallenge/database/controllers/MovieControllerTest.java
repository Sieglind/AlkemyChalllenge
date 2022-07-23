package ar.com.cdmoraleda.alkemychallenge.database.controllers;

import ar.com.cdmoraleda.alkemychallenge.database.dto.CharacterDto;
import ar.com.cdmoraleda.alkemychallenge.database.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.database.services.CharacterService;
import ar.com.cdmoraleda.alkemychallenge.database.services.GenreService;
import ar.com.cdmoraleda.alkemychallenge.database.services.MovieService;
import ar.com.cdmoraleda.alkemychallenge.security.models.ApiUser;
import ar.com.cdmoraleda.alkemychallenge.security.services.ApiUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private MovieService movieService;
    @MockBean
    private CharacterService characterService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private ApiUserService apiUserService;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Test
    void shouldReturnCreatedMovie() throws Exception {
        //given A MovieDto object with it's associated characters
        new MovieDto();
        MovieDto movieDto = MovieDto.builder()
                .pictUrl("aMovieUrl")
                .title("aMovieTitle")
                .releaseYear(2000)
                .score(5)
                .assocCharacters(new ArrayList<>())
                .build();
        new CharacterDto();
        CharacterDto characterDto = CharacterDto.builder()
                .pictUrl("aCharacterUrl")
                .name("aCharacterName")
                .age(1)
                .weight("characterWeight")
                .history("characterHistory")
                .build();
        movieDto.getAssocCharacters().add(characterDto);
        given(movieService.createMovie(any(MovieDto.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));
        //when Sending a POST request to the endpoint /movies with the MovieDto in JSON format included in the body
        ResultActions response = mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieDto)));
        //then It should respond with the created Movie parsed to a MovieDto and send in JSON format in the body
        response.andDo(print()).
                andExpect(status().isCreated())
//                .andExpect(jsonPath("$.movieId",
//                        is(movieDto.getMovieId())))
                .andExpect(jsonPath("$.pictUrl",
                        is(movieDto.getPictUrl())))
                .andExpect(jsonPath("$.title",
                        is(movieDto.getTitle())))
                .andExpect(jsonPath("$.releaseYear",
                        is(movieDto.getReleaseYear())))
                .andExpect(jsonPath("$.score",
                        is(movieDto.getScore())));
    }


    @Test
    void findMovieByTitle() {
    }

    @Test
    void filterMoviesByGenre() {
    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovie() {
    }

    @Test
    void addCharacterToMovie() {
    }

    @Test
    void removeCharacterFromMovie() {
    }
}