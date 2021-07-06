package com.gussoft.stream.services.impl;

import com.gussoft.stream.models.Detalle;
import com.gussoft.stream.repository.DetalleRepository;
import com.gussoft.stream.services.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleServiceImpl implements DetalleService {

    @Autowired
    private DetalleRepository repo;

    @Override
    public void saveDetalle(Detalle detalle) {
        repo.save(detalle);
    }
}
