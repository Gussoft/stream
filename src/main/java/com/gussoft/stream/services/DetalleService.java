package com.gussoft.stream.services;

import com.gussoft.stream.models.Detalle;

public interface DetalleService {

    void saveDetalle(Detalle detalle);

    Detalle getDetalle(Long id);

    void DeletedDetalle(Long id);

}
