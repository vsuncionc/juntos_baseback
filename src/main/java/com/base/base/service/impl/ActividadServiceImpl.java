package com.base.base.service.impl;

import com.base.base.dto.request.ConsultActividadRequest;
import com.base.base.dto.request.RegistroPlanAnualActividad;
import com.base.base.model.Actividad;
import com.base.base.repository.ActividadRepository;
import com.base.base.service.ActividadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActividadServiceImpl implements ActividadService {

private final ActividadRepository actividadRepository;
    @Override
    public List<Actividad> busquedaActividad(ConsultActividadRequest parametros) {
        return actividadRepository.busquedaActividad(parametros);
    }

    @Override
    public void insertarPlanActividad(RegistroPlanAnualActividad registro) {
        actividadRepository.insertarPlanActividad(registro);
    }
}
