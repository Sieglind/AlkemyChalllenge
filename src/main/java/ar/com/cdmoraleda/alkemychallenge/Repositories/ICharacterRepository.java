package ar.com.cdmoraleda.alkemychallenge.Repositories;

import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ICharacterRepository extends CrudRepository<Character, Integer> {
    Optional<Character> findByName(String name);
    List<Character> findByNameContaining(String name);
    List<Character> findByAge(Integer age);
    List<Character> findByWeight(String weight);
    List<Character> findByAssocMovies(Movie movie);
}
