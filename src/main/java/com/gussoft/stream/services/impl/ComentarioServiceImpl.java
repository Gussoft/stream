package com.gussoft.stream.services.impl;

import com.gussoft.stream.models.Comentario;
import com.gussoft.stream.repository.ComentarioRepository;
import com.gussoft.stream.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository repo;

    @Override
    public void SavedComentario(Comentario comentario) {
        repo.save(comentario);
    }
}
