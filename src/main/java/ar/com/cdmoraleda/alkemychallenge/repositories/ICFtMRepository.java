package ar.com.cdmoraleda.alkemychallenge.repositories;

import ar.com.cdmoraleda.alkemychallenge.models.CFtM;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICFtMRepository extends CrudRepository<CFtM, Integer> {
    List<CFtM> findByCharacterNameAndAndMovieName(String characterName, String movieName);

    void deleteByCharacterName(String characterName);
}
