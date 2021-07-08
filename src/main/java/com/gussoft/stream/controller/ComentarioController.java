package com.gussoft.stream.controller;

import com.gussoft.stream.models.Comentario;
import com.gussoft.stream.models.Usuario;
import com.gussoft.stream.services.ComentarioService;
import com.gussoft.stream.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/edit-comentario/{id}")
    public String editarComentario(@PathVariable(name = "id")Long id, Model model){
        model.addAttribute("comentario", comentarioService.buscarporId(id));

        return "comentarios/editCom";
    }

    @GetMapping("/editar")
    public String editar(@ModelAttribute("comentario")Comentario comentario,
                         RedirectAttributes mensaje, Authentication auth, HttpSession session){
        String username = auth.getName();
        Optional<Usuario> usuario = usuarioService.getByUsername(username);
        comentario.setUsuario(usuario.get());
        comentario.setFecha(new Date());
        comentarioService.SavedComentario(comentario);
        mensaje.addFlashAttribute("comentarioModificado", "Comentario Editado!");
        return "redirect:/";
    }

    @GetMapping("/delet-comentario/{id}")
    public String eliminar(@PathVariable(name = "id")Long id,RedirectAttributes mensaje){

        comentarioService.deleteComentario(id);
        mensaje.addFlashAttribute("comentarioEliminado", "Comentario Eliminado!");

        return "redirect:/";
    }
}
