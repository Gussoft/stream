package com.gussoft.stream.controller;

import com.gussoft.stream.models.Detalle;
import com.gussoft.stream.models.Serie;
import com.gussoft.stream.repository.DetalleRepository;
import com.gussoft.stream.services.DetalleService;
import com.gussoft.stream.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/detalles") //detalles/detail
@SessionAttributes("seriefordetalle")
public class DetalleController {

    @Autowired
    private DetalleService service;

    @Autowired
    private SerieService serieService;

    @GetMapping("/detail")
    public String DetallesForm(Detalle detalle, Model model, @ModelAttribute("seriefordetalle")Serie serie){
        model.addAttribute("detalle", new Detalle());
        model.addAttribute("serie", serie); // film
        return "admin/detalles";
    }

    @PostMapping("/saved")
    public String saveDetalle(Detalle detalle, RedirectAttributes mensaje, Model model,
                              @ModelAttribute("seriefordetalle")Serie serie){
        service.saveDetalle(detalle);
        mensaje.addFlashAttribute("detalleSaved", "Se Guardo un Detalle!");

        return "redirect:/detalles/detail";
    }

    @GetMapping("/add-serie/{id}")
    public String addDetalle(@PathVariable Long id, Model model){
        Serie serie = serieService.seriebyId2(id);

        model.addAttribute("detalle", new Detalle());
        model.addAttribute("serie", serie);
        return "admin/adddetalleform";
    }
}
