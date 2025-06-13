package br.com.juliocesarcoutinho.userservice.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.juliocesarcoutinho.userservice.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u JOIN FETCH u.permissions p WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
}