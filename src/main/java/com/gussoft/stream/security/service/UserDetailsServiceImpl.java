package com.gussoft.stream.security.service;

import com.gussoft.stream.models.Usuario;
import com.gussoft.stream.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        Usuario usuario = service.getByUsername(user).orElseThrow(()->
                new UsernameNotFoundException(user));
        return UsuarioPrincipal.build(usuario);
    }
}
