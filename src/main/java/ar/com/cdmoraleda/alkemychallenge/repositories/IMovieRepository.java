package ar.com.cdmoraleda.alkemychallenge.repositories;

import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends CrudRepository<Movie, Integer> {
    List<Movie> findByTitle(String title);
}
