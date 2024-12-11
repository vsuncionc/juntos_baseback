package com.base.base.repository;

import com.base.base.dto.request.PreguntaRequest;

public interface PreguntaRepository {
    int crearPregunta(PreguntaRequest parametros);
}
