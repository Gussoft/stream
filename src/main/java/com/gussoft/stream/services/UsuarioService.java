package com.gussoft.stream.services;

import com.gussoft.stream.models.Usuario;
import com.gussoft.stream.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public List<Usuario> listarUsuarios(){
        return repo.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id){
        return repo.findById(id);
    }

    public Optional<Usuario> getByUsername(String username){
        return repo.findByUsername(username);
    }

    public void savedUsuario(Usuario usuario){
        repo.save(usuario);
    }

    public boolean existsById(Long id){
        return repo.existsById(id);
    }

    public boolean existsByUsername(String username){
        return repo.existsByUsername(username);
    }
}
