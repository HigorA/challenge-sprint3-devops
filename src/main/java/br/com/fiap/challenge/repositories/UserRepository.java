package br.com.fiap.challenge.repositories;

import br.com.fiap.challenge.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email =:email and enabled = true")
    Optional<User> findByUsername(@Param("email") String email);


}
