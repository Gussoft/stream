package com.gussoft.stream.repository;

import com.gussoft.stream.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    Serie findByNombre(String nombre);

}
