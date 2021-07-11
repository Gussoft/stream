package com.gussoft.stream.controller;

import com.gussoft.stream.models.Roles;
import com.gussoft.stream.models.Usuario;
import com.gussoft.stream.services.RolesService;
import com.gussoft.stream.services.UsuarioService;
import com.gussoft.stream.utils.RolNombre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    private String registrar(){
        return "registro";
    }

    @PostMapping("/saved")
    public String savedUsuario(String username, String password, Model model, RedirectAttributes mensaje){
        if (usuarioService.existsByUsername(username)){
            model.addAttribute("usuarioreperido", "El Usuario ya Existe!");
            return "registro";
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));

        Roles rolUser = rolesService.getByRolNombre(RolNombre.ROLE_USER).get();
        Set<Roles> roles = new HashSet<>();
        roles.add(rolUser);

        usuario.setRoles(roles);
        usuarioService.savedUsuario(usuario);
        //model.addAttribute("userregistrado", "1 registro Complelto, inicie Session!");
        mensaje.addFlashAttribute("userregistrado", "2 registro Complelto, inicie Session!");
        return "redirect:/login";
    }
}
