package com.base.base.repository;

import com.base.base.dto.request.ActActivodadColegioRequest;
import com.base.base.dto.request.ConsultaColegiosRequest;
import com.base.base.dto.request.RegistrarColegioPlanRequest;
import com.base.base.model.ActividadPlanColegio;
import com.base.base.model.Colegio;
import com.base.base.model.PlanAnualnstitucion;

import java.util.List;

public interface ColegiosRepository {
    List<Colegio> buscarColegios(ConsultaColegiosRequest consultaColegiosRequest);
    int insertarPlanColegio(RegistrarColegioPlanRequest request);

    void actualizarActividad(ActActivodadColegioRequest request);

    List<ActividadPlanColegio> listaActividadPorColegio(ActActivodadColegioRequest request);
    List<PlanAnualnstitucion> listarPlanesPorColegio(int codigoColegio);
}
