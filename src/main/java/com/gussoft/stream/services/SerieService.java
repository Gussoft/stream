package com.gussoft.stream.services;

import com.gussoft.stream.models.Serie;

import java.util.List;
import java.util.Optional;

public interface SerieService {

    void save(Serie serie);

    List<Serie> listarSeries();

    Optional<Serie> seriebyId(Long id);

    //forma 2 // debes pasarle un OrElse(null)
    Serie seriebyId2(Long id);

    void deletedSerie(Long id);

    Serie getSerieByNombre(String nombre);

}
