package com.base.base.api;

import com.base.base.convert.PlanAnualConvert;
import com.base.base.dto.request.ConsultaPlanAnualRequest;
import com.base.base.model.PlanAnual;
import com.base.base.model.ResponseAppBean;
import com.base.base.service.PlanAnualService;
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


import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/plananual")
@RequiredArgsConstructor
public class PlanAnualController {

    private final PlanAnualService planAnualService;
    private  final PlanAnualConvert planAnualConvert;

    @PostMapping(value = "listarplanes", produces = "application/json")
    public ResponseEntity<?> listarplanes(@RequestBody ConsultaPlanAnualRequest consultaGenerico, HttpServletRequest request, HttpServletResponse response) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
       try {
           List<PlanAnual> lista = planAnualService.buscarPlanes(consultaGenerico);
           responseAppBean.setData(planAnualConvert.convertConsultaPlanAnualToDto(lista));
           responseAppBean.setStatus(Constante.RESPONSE_OK);
             return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
           } catch (Exception e) {
            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(Constante.MENSAJE_ERROR);
           responseAppBean.setMessageBackend(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.ACCEPTED);
        }
    }

    @PostMapping(value = "nuevoplan", produces = "application/json")
    public ResponseEntity<?> nuevoPlan(@Valid @RequestBody  ConsultaPlanAnualRequest consultaGenerico, HttpServletRequest request, HttpServletResponse response) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
           planAnualService.insertarPlan(consultaGenerico);
            responseAppBean.setStatus(Constante.RESPONSE_OK);
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e) {
            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(Constante.MENSAJE_ERROR);
            responseAppBean.setMessageBackend(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.ACCEPTED);
        }
    }





}
