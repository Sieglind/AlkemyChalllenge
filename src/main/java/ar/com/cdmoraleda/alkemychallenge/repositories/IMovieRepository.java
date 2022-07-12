package ar.com.cdmoraleda.alkemychallenge.repositories;

import org.springframework.data.repository.CrudRepository;
import ar.com.cdmoraleda.alkemychallenge.models.Genre;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IMovieRepository extends CrudRepository<Movie, Integer> {
    Movie findByTitle(String name);

    List<Movie> findByAsoccGenresOrderByReleaseYearAsc(Genre genre);

    List<Movie> findByAsoccGenresOrderByReleaseYearDesc(Genre genre);
}
