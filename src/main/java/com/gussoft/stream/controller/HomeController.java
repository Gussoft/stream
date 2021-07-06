package com.gussoft.stream.controller;

import com.gussoft.stream.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private SerieService service;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("series", service.listarSeries());
        return "home";
    }
}
