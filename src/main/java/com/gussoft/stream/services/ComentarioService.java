package com.gussoft.stream.services;

import com.gussoft.stream.models.Comentario;

public interface ComentarioService {

    void SavedComentario(Comentario comentario);

    Comentario buscarporId(Long id);

    void deleteComentario(Long id);

}
