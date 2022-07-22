package ar.com.cdmoraleda.alkemychallenge.database.Repositories;

import ar.com.cdmoraleda.alkemychallenge.database.models.Genre;
import org.springframework.data.repository.CrudRepository;
import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IMovieRepository extends CrudRepository<Movie, Integer> {
    Optional<Movie> findByTitle(String title);
    List<Movie> findMovieByTitleContainingOrderByReleaseYearAsc(String title);
    List<Movie> findMovieByTitleContainingOrderByReleaseYearDesc(String name);
    List<Movie> findByAssocGenresOrderByReleaseYearAsc(Genre genre);
    List<Movie> findByAssocGenresOrderByReleaseYearDesc(Genre genre);
}
