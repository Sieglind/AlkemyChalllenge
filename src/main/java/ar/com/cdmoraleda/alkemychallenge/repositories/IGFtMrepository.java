package ar.com.cdmoraleda.alkemychallenge.repositories;

import ar.com.cdmoraleda.alkemychallenge.models.GFtM;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGFtMrepository extends CrudRepository<GFtM, Integer> {
}
