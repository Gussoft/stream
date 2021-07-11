package com.gussoft.stream.services.impl;

import com.gussoft.stream.models.Serie;
import com.gussoft.stream.repository.SerieRepository;
import com.gussoft.stream.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieServiceImpl implements SerieService {

    @Autowired
    private SerieRepository repo;

    @Override
    public void save(Serie serie) {
        repo.save(serie);
    }

    @Override
    public List<Serie> listarSeries() {
        return repo.findAll();
    }

    @Override
    public Optional<Serie> seriebyId(Long id) {
        return repo.findById(id);
    }

    @Override
    public Serie seriebyId2(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deletedSerie(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Serie getSerieByNombre(String nombre) {
        return repo.findByNombre(nombre);
    }
}
