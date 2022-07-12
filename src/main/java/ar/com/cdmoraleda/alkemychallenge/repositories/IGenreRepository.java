package ar.com.cdmoraleda.alkemychallenge.repositories;

import org.springframework.data.repository.CrudRepository;
import ar.com.cdmoraleda.alkemychallenge.models.Genre;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenreRepository extends CrudRepository<Genre, Integer> {
}
