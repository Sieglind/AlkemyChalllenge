package ar.com.cdmoraleda.alkemychallenge.Repositories;

import ar.com.cdmoraleda.alkemychallenge.models.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApiUserRepository extends JpaRepository<ApiUser,Integer> {
    ApiUser findByUsername(String username);
    ApiUser findByEmail(String userName);
}
