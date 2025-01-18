package by.meshicage.authenticationservice.repository;

import by.meshicage.authenticationservice.entity.RoleEntity;
import by.meshicage.authenticationservice.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRole(Role role);
}
