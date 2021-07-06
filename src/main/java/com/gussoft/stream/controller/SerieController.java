package com.gussoft.stream.controller;

import com.gussoft.stream.models.Comentario;
import com.gussoft.stream.models.Serie;
import com.gussoft.stream.models.Usuario;
import com.gussoft.stream.services.ComentarioService;
import com.gussoft.stream.services.SerieService;
import com.gussoft.stream.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/series")
@SessionAttributes("comentario")
public class SerieController {

    @Autowired
    private SerieService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/ver-serie/{id}")
    public String verSerie(@PathVariable Long id, Model model){ // ver-comentarios
        Serie serie = service.seriebyId2(id);
        model.addAttribute("serie", serie);

        return "series/verserie";
    }

    @GetMapping("/comentario/{id}")
    public String SerieComentario(@PathVariable Long id, Model model){
        Serie serie = service.seriebyId2(id);
        Comentario comentario = new Comentario();
        comentario.setSerie(serie);

        model.addAttribute("comentario", comentario);
        model.addAttribute("serie", serie);

        return "comentarios/comentario";
    }

    @PostMapping("/savedcomentario")
    public String comentarioSaved(Comentario comentario, Authentication auth, HttpSession session,
                                  RedirectAttributes mensaje){
        String username = auth.getName();
        Optional<Usuario> usuario = usuarioService.getByUsername(username);

        comentario.setUsuario(usuario.get());
        comentarioService.SavedComentario(comentario);

        mensaje.addFlashAttribute("savedcomentario", "Comentario Guardado!");
        return "redirect:/";
    }
}
