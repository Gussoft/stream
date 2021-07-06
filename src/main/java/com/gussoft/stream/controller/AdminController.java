package com.gussoft.stream.controller;

import com.gussoft.stream.models.Serie;
import com.gussoft.stream.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SerieService service;

    @GetMapping("/serie") // peli-form
    public String serieForm(Model model){
        model.addAttribute("serie", new Serie());
        return "admin/serie"; //peliForm
    }

    @PostMapping("/saved")
    public String saveSerie(@RequestParam(name = "fileb", required = false)MultipartFile banner, Serie serie,
                            RedirectAttributes mensaje, @RequestParam(name = "filet", required = false)MultipartFile thumbs){

        if (!banner.isEmpty() && !thumbs.isEmpty()){
            String ruta = "C:\\temp\\uploads";
            String nombreBanner = UUID.randomUUID() + " " + banner.getOriginalFilename();
            String nombreThumbs = UUID.randomUUID() + " " + thumbs.getOriginalFilename();
            try {
                byte[] bytes = banner.getBytes();
                byte[] bytes1 = thumbs.getBytes();
                Path rutaBanner = Paths.get(ruta + "\\" + nombreBanner);
                Path rutaThumbs = Paths.get(ruta + "\\" + nombreThumbs);
                Files.write(rutaBanner,bytes);
                Files.write(rutaThumbs,bytes1);

                serie.setBanner(nombreBanner);
                serie.setThumbs(nombreThumbs);

                service.save(serie);
                mensaje.addFlashAttribute("SerieSaved", "Serie Guardada con Exito!");
                mensaje.addFlashAttribute("seriefordetalle", serie);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "redirect:/detalles/detail"; // "redirect:/admin/serie"
    }

}
