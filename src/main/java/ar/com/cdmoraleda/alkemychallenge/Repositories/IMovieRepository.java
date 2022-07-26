package ar.com.cdmoraleda.alkemychallenge.Repositories;

import ar.com.cdmoraleda.alkemychallenge.dto.MovieDto;
import ar.com.cdmoraleda.alkemychallenge.models.Genre;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface IMovieRepository extends CrudRepository<Movie, Integer> {
    Optional<Movie> findByTitle(String title);
    List<Movie> findByAssocGenres(Genre genre);
    List<Movie> findByTitleContainingAndAssocGenres(String title, Genre genre);

    List<Movie> findByTitleContaining(String name);
    List<Movie> findByAssocGenres(Genre genre, Sort releaseYear);
    List<Movie> findByTitleContaining(String name, Sort releaseYear);
    List<Movie> findByTitleContainingAndAssocGenres(String title, Genre genre, Sort releaseYear);

}
