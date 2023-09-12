package br.com.fiap.challenge.repositories;

import br.com.fiap.challenge.model.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("SELECT f FROM Favorite f WHERE f.user.idUser =:idUser ")
    List<Favorite> findAllByUserId(@Param("idUser") Long idUser);
}
