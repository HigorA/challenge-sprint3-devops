package br.com.fiap.challenge.repositories;

import br.com.fiap.challenge.model.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
