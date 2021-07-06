package com.gussoft.stream.repository;

import com.gussoft.stream.models.Roles;
import com.gussoft.stream.utils.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Optional<Roles> findByRolNombre(RolNombre rolNombre);

    boolean existsByRolNombre(RolNombre rolNombre);
}
