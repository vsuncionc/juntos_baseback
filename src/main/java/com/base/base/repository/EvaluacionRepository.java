package com.base.base.repository;

import com.base.base.dto.request.EvaluacionRequest;

public interface EvaluacionRepository {
    int RegistrarEvaluacion(EvaluacionRequest parametros);
}
