package com.base.base.service;

import com.base.base.dto.request.ActActivodadColegioRequest;
import com.base.base.dto.request.ConsultaColegiosRequest;
import com.base.base.dto.request.RegistrarColegioPlanRequest;
import com.base.base.dto.response.InformacionArchivo;
import com.base.base.model.ActividadPlanColegio;
import com.base.base.model.Colegio;
import com.base.base.model.PlanAnualnstitucion;

import java.util.List;
import java.util.Map;

public interface ColegiosService {
    List<Colegio> buscarColegios(ConsultaColegiosRequest consultaColegiosRequest);
    int insertarPlanColegio(RegistrarColegioPlanRequest request);
    void actualizarActividad(ActActivodadColegioRequest request);
    List<ActividadPlanColegio> listaActividadPorColegio(ActActivodadColegioRequest request);
    Map<String, String> subirArchivo(InformacionArchivo archivo,ActActivodadColegioRequest info) throws Exception;
    List<PlanAnualnstitucion> listarPlanesPorColegio(int codigoColegio);
}
