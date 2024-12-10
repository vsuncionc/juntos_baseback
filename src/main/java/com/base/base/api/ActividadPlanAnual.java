package com.base.base.api;

import com.base.base.convert.ActividadConvert;
import com.base.base.dto.request.ConsultActividadRequest;
import com.base.base.dto.request.RegistroPlanAnualActividad;
import com.base.base.model.ResponseAppBean;
import com.base.base.service.ActividadService;
import com.base.base.util.Constante;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value = "/actividad")
@RequiredArgsConstructor
public class ActividadPlanAnual {

    private final ActividadService actividadService;
    private final ActividadConvert actividadConvert;

@PostMapping(value = {"buscar"})
public ResponseEntity<?> listarActividades(@RequestBody ConsultActividadRequest consultActividadRequest, HttpServletRequest request, HttpServletResponse response) {
    ResponseAppBean responseAppBean = new ResponseAppBean();
    try {
        responseAppBean.setData(actividadConvert.ConvertActividadToDto(actividadService.busquedaActividad(consultActividadRequest)));
        responseAppBean.setStatus(Constante.RESPONSE_OK);
        return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
    }catch (Exception e){
        responseAppBean.setStatus(Constante.RESPONSE_ERROR);
        responseAppBean.setMessage(Constante.MENSAJE_ERROR);
        responseAppBean.setMessageBackend(e.getMessage());
        return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


    @PostMapping(value = "registraractividad", produces = "application/json")
    public ResponseEntity<?> nuevoPlan(@Valid @RequestBody RegistroPlanAnualActividad registro, HttpServletRequest request, HttpServletResponse response) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            actividadService.insertarPlanActividad(registro);
            responseAppBean.setStatus(Constante.RESPONSE_OK);
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(Constante.MENSAJE_ERROR);
            responseAppBean.setMessageBackend(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
