package ar.com.cdmoraleda.alkemychallenge.security.dto.repositories;

import ar.com.cdmoraleda.alkemychallenge.database.models.Genre;
import org.springframework.data.repository.CrudRepository;
import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IMovieRepository extends CrudRepository<Movie, Integer> {
    Movie findByTitle(String name);

    List<Movie> findByAsoccGenresOrderByReleaseYearAsc(Genre genre);

    List<Movie> findByAsoccGenresOrderByReleaseYearDesc(Genre genre);
}
