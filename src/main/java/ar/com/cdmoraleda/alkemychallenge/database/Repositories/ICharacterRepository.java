package ar.com.cdmoraleda.alkemychallenge.database.Repositories;

import ar.com.cdmoraleda.alkemychallenge.database.models.Character;
import ar.com.cdmoraleda.alkemychallenge.database.models.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ICharacterRepository extends CrudRepository<Character, Integer> {
    Optional<Character> findByName(String name);

    Optional<Character> findByNameContaining(String name);

    List<Character> findByAge(Integer age);

    List<Character> findByWeight(String weight);

    List<Character> findByAssocMovies(Movie movie);
}
