package com.base.base.repository;

import com.base.base.dto.request.EvaluacionRequest;

import java.text.ParseException;

public interface EvaluacionRepository {
    int RegistrarEvaluacion(EvaluacionRequest parametros) throws ParseException;
}
