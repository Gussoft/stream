package com.gussoft.stream.services;

import com.gussoft.stream.models.Roles;
import com.gussoft.stream.repository.RolesRepository;
import com.gussoft.stream.utils.RolNombre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesService {

    @Autowired
    private RolesRepository repo;

    public void savedRoles(Roles roles){
        repo.save(roles);
    }

    public Optional<Roles> getByRolNombre(RolNombre rolNombre){
        return repo.findByRolNombre(rolNombre);
    }

    public boolean existsByRolNombre(RolNombre rolNombre){
        return repo.existsByRolNombre(rolNombre);
    }
}
