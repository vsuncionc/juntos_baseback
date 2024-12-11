package com.base.base.service.impl;

import com.base.base.dto.request.EvaluacionRequest;
import com.base.base.repository.EvaluacionRepository;
import com.base.base.service.EvaluacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EvaluacionServiceImpl implements EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    @Override
    public int RegistrarEvaluacion(EvaluacionRequest parametros) throws ParseException {
        return evaluacionRepository.RegistrarEvaluacion(parametros);
    }
}
