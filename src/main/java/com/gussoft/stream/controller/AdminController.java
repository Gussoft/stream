package com.gussoft.stream.controller;

import com.gussoft.stream.models.Detalle;
import com.gussoft.stream.models.Serie;
import com.gussoft.stream.services.DetalleService;
import com.gussoft.stream.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private DetalleService detalleService;

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

    @GetMapping("/gestion-series")
    public String listadSeries(Model model){
        model.addAttribute("serie", service.listarSeries());
        return "admin/gestionseries";
    }

    @GetMapping("/delete-serie/{id}")
    public String deletedSerie(@PathVariable Long id, RedirectAttributes mensaje){
        service.deletedSerie(id);
        mensaje.addFlashAttribute("seriedelete", "Serie Eliminada!");
        return "redirect:/admin/gestion-series";
    }

    @GetMapping("/edit-serie/{id}")
    public String editSerie(@PathVariable Long id, RedirectAttributes mensaje, Model model){

        Serie serie =  null;
        if (id > 0){
            serie = service.seriebyId2(id);
            model.addAttribute("serie", serie);
        }
        return "admin/editarserie";
    }

    @PostMapping("/edit-serie")
    public String updateSerie(@RequestParam(name = "file1")MultipartFile banner,@RequestParam(name = "file2")MultipartFile thumbs, Serie serie
    , RedirectAttributes mensaje, @ModelAttribute("serie")Serie serieMA, Model model){
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
                mensaje.addFlashAttribute("SerieEdit", "Serie Editada con Exito!");

            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            service.save(serie);
            mensaje.addFlashAttribute("SerieEdit", "Serie Guardada con Exito! sin Imagenes");
        }

        return "redirect:/admin/gestion-series";
    }

    @GetMapping("/edit-detalles/{id}")
    public String editDetalle(@PathVariable Long id, RedirectAttributes mensaje, Model model){
        Serie serie = service.seriebyId2(id);
        model.addAttribute("serieOk", serie);
        return "admin/editdetalle";
    }

    @GetMapping("/cargar-detalle/{id}")
    public String cargarDetalle(@PathVariable Long id, RedirectAttributes mensaje, Model model){
        Detalle detalle = detalleService.getDetalle(id);
        model.addAttribute("detalle", detalle);
        return "admin/editardetalleform";
    }

    @PostMapping("/editar-detalle")
    public String editarDetalleS(@ModelAttribute("detalle")Detalle detalle, RedirectAttributes mensaje, Model model){
        detalleService.saveDetalle(detalle);
        mensaje.addFlashAttribute("detalleEditado", "Detalle Modificado!");
        return "redirect:/admin/gestion-series";
    }

    @GetMapping("/delete-detalle/{id}")
    public String eliminarDetalle(@PathVariable Long id, RedirectAttributes mensaje, Model model){
        detalleService.DeletedDetalle(id);
        mensaje.addFlashAttribute("detalleEliminado", "Detalle Eliminado!");
        return "redirect:/admin/gestion-series";
    }

    @PostMapping("/detalle-saved")
    public String saveDetalle(Detalle detalle, RedirectAttributes mensaje, Model model){
        detalleService.saveDetalle(detalle);
        mensaje.addFlashAttribute("detalleSaved", "Se Guardo un Detalle!");

        return "redirect:/admin/gestion-series";
    }
}
