package com.base.base.repository;

import com.base.base.dto.request.ConsultActividadRequest;
import com.base.base.dto.request.RegistroPlanAnualActividad;
import com.base.base.model.Actividad;

import java.util.List;

public interface ActividadRepository {
    List<Actividad> busquedaActividad(ConsultActividadRequest parametros);
    void insertarPlanActividad(RegistroPlanAnualActividad registro);
}
