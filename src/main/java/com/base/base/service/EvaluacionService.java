package com.base.base.service;

import com.base.base.dto.request.EvaluacionRequest;

import java.text.ParseException;

public interface EvaluacionService {
    int  RegistrarEvaluacion(EvaluacionRequest parametros) throws ParseException;
}
