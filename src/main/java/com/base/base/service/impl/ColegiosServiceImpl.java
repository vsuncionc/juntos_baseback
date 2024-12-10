package com.base.base.service.impl;

import com.base.base.dto.request.ActActivodadColegioRequest;
import com.base.base.dto.request.ConsultaColegiosRequest;
import com.base.base.dto.request.RegistrarColegioPlanRequest;
import com.base.base.dto.response.InformacionArchivo;
import com.base.base.model.ActividadPlanColegio;
import com.base.base.model.Colegio;
import com.base.base.model.PlanAnualnstitucion;
import com.base.base.repository.ColegiosRepository;
import com.base.base.service.ColegiosService;
import com.base.base.util.UploadUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ColegiosServiceImpl implements ColegiosService {

    private final ColegiosRepository colegiosRepository;
    @Override
    public List<Colegio> buscarColegios(ConsultaColegiosRequest consultaColegiosRequest) {
        return colegiosRepository.buscarColegios(consultaColegiosRequest);
    }

    @Override
    public int insertarPlanColegio(RegistrarColegioPlanRequest request) {
      return colegiosRepository.insertarPlanColegio(request);
    }

    @Override
    public void actualizarActividad(ActActivodadColegioRequest request) {
        colegiosRepository.actualizarActividad(request);
    }

    @Override
    public List<ActividadPlanColegio> listaActividadPorColegio(ActActivodadColegioRequest request) {
        return colegiosRepository.listaActividadPorColegio(request);
    }

    @Override
    public Map<String, String> subirArchivo(InformacionArchivo archivo,ActActivodadColegioRequest info) throws Exception {
        Map<String, String> respuesta = new HashMap<>();
        String nombreArchivo = "";

        // PREGUNTAMOS SI A EXTENSION ES CORRECTA
        if(!archivo.getExtension().toUpperCase().equals("PDF")){
            respuesta.put("1","ERROR : LA EXTENSION DEL ARCHIVO("+archivo.getExtension().toUpperCase()+") NO ES VALIDA SOLO SE PERMITE ARCHIVO PDF");
            return respuesta;
        }
        //SUBIMOS EL ARCHIVO
        UploadUtility.saveFileToDiskArchivoSustento(archivo,String.valueOf(info.getCodigoColegio()));
        respuesta.put("0",null);
        return respuesta;
    }

    @Override
    public List<PlanAnualnstitucion> listarPlanesPorColegio(int codigoColegio) {
        return colegiosRepository.listarPlanesPorColegio(codigoColegio);
    }
}
