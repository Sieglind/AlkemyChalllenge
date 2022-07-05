package ar.com.cdmoraleda.alkemychallenge.repositories;

import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface IMovieRepository extends CrudRepository<Movie, Integer> {

}
