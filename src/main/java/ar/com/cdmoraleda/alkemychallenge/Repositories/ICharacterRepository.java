package ar.com.cdmoraleda.alkemychallenge.Repositories;

import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface ICharacterRepository extends CrudRepository<Character, Integer> {
    Optional<Character> findByName(String name);
    Set<Character> findByNameContaining(String name);
    Set<Character> findByAge(Integer age);
    Set<Character> findByWeight(String weight);
    Set<Character> findByAssocMovies(Movie movie);
}
