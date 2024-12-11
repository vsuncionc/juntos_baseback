package com.base.base.api;

import com.base.base.dto.request.ConsultaPlanAnualRequest;
import com.base.base.dto.request.PreguntaRequest;
import com.base.base.model.ResponseAppBean;
import com.base.base.service.PreguntaService;
import com.base.base.util.Constante;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
@RequestMapping(value = "/pregunta")
@RequiredArgsConstructor
public class PreguntaController {

    private final PreguntaService preguntaService;

    @PostMapping(value = "crearpregunta", produces = "application/json")
    public ResponseEntity<?> crearPregunta(@RequestBody PreguntaRequest parametros, HttpServletRequest request, HttpServletResponse response) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {

            responseAppBean.setData(preguntaService.crearPregunta(parametros));
            responseAppBean.setStatus(Constante.RESPONSE_OK);
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        } catch (Exception e) {
            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(Constante.MENSAJE_ERROR);
            responseAppBean.setMessageBackend(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.ACCEPTED);
        }
    }
}
