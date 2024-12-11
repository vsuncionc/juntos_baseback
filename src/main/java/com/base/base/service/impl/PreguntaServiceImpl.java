package com.base.base.service.impl;

import com.base.base.dto.request.PreguntaRequest;
import com.base.base.repository.PreguntaRepository;
import com.base.base.service.PreguntaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PreguntaServiceImpl implements PreguntaService {
   private final PreguntaRepository preguntaRepository;
    @Override
    public int crearPregunta(PreguntaRequest parametros) {
        return preguntaRepository.crearPregunta(parametros);
    }
}
