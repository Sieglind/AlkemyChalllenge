package ar.com.cdmoraleda.alkemychallenge.Repositories;

import ar.com.cdmoraleda.alkemychallenge.models.Genre;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface IMovieRepository extends CrudRepository<Movie, Integer> {
    Optional<Movie> findByTitle(String title);
    Set<Movie> findByAssocGenres(Genre genre);
    Set<Movie> findByTitleContainingAndAssocGenres(String title, Genre genre);

    Set<Movie> findByTitleContaining(String name);
    Set<Movie> findByAssocGenres(Genre genre, Sort releaseYear);
    Set<Movie> findByTitleContaining(String name, Sort releaseYear);
    Set<Movie> findByTitleContainingAndAssocGenres(String title, Genre genre, Sort releaseYear);

}
