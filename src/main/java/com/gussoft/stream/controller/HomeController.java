package com.gussoft.stream.controller;

import com.gussoft.stream.models.Serie;
import com.gussoft.stream.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class HomeController {

    @Autowired
    private SerieService service;

    @GetMapping("/")
    public String home(Model model, Authentication auth){
        if (auth != null){
            String username = auth.getName();
            model.addAttribute("username", username);
        }
        model.addAttribute("series", service.listarSeries());
        model.addAttribute("serie", new Serie());
        return "home";
    }

    @GetMapping("/search")
    public String getSerieByName(@ModelAttribute("serie") Serie serie, Model model, String name){
        Serie s = service.getSerieByNombre(name);
        if (s != null){
            model.addAttribute("serie", s);
        }
        if(s == null){
            model.addAttribute("nofound", "Digite Correctamente el nombre de la serie!");
        }
        return "search/searchserie";
    }

    @GetMapping("/gallery")
    public String gallery(Model model){
        model.addAttribute("series", service.listarSeries());
        model.addAttribute("serie", new Serie());
        return "search/searchserie";
    }
}
