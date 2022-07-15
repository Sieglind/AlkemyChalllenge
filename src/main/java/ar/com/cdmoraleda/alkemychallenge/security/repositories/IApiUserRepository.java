package ar.com.cdmoraleda.alkemychallenge.security.repositories;

import ar.com.cdmoraleda.alkemychallenge.security.models.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApiUserRepository extends JpaRepository<ApiUser,Integer> {
    ApiUser findByUserName(String userName);

    ApiUser findByEmail(String userName);
}
