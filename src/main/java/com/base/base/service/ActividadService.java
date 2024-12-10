package com.base.base.service;

import com.base.base.dto.request.ConsultActividadRequest;
import com.base.base.dto.request.RegistroPlanAnualActividad;
import com.base.base.model.Actividad;

import java.util.List;

public interface ActividadService {

    List<Actividad> busquedaActividad(ConsultActividadRequest parametros);
    void insertarPlanActividad(RegistroPlanAnualActividad registro);
}
