package ar.com.cdmoraleda.alkemychallenge.repositories;

import ar.com.cdmoraleda.alkemychallenge.models.Character;
import ar.com.cdmoraleda.alkemychallenge.models.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICharacterRepository extends CrudRepository<Character, Integer> {
    Character findByName(String name);

    List<Character> findByAge(Integer age);

    List<Character> findByWeight(String weight);

    List<Character> findByAsoccMovies(Movie movie);
}
