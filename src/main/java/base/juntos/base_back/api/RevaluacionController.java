package base.juntos.base_back.api;

import base.juntos.base_back.convert.RevaluacionDtoConvert;
import base.juntos.base_back.dto.request.*;
import base.juntos.base_back.model.ResponseAppBean;
import base.juntos.base_back.service.GenerarExcelService;
import base.juntos.base_back.service.RevaluacionService;
import base.juntos.base_back.util.Constantes;
import base.juntos.base_back.util.FechasUtilitarios;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/revaluacion")
@RequiredArgsConstructor
public class RevaluacionController {

    private final RevaluacionService revaluacionService;
    private final RevaluacionDtoConvert revaluacionDtoConvert;

    @PostMapping(value ="inforevaluacion")
    public ResponseEntity<?> infoRevaluacion(@RequestBody RevaluacionRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setData(revaluacionService.infoRevaluacion(parametros));
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value ="buscar")
    public ResponseEntity<?> buscar(@RequestBody RevaluacionRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setData(revaluacionDtoConvert.convertRevaluacionDto(revaluacionService.buscarRevaluaciones(parametros)));
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping(value ="listarMiembrosHogarRevaluacion")
    public ResponseEntity<?> buscarMiembrosHogar(@RequestBody  RevaluacionRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
           // revaluacionService.buscarMhRevaluacion(parametros);
            responseAppBean.setData(revaluacionDtoConvert.convertRevaluacionMhDto(revaluacionService.buscarMhRevaluacion(parametros)));
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value ="listarMiembrosObjetivosRevaluacion")
    public ResponseEntity<?> buscarObjetivosHogar(@RequestBody  RevaluacionRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setData(revaluacionDtoConvert.convertRevaluacionMoDto(revaluacionService.buscarMoRevaluacion(parametros)));
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "listrevseleccionadas")
    public ResponseEntity<?> listaRevaluacionesSeleccionadas(@RequestBody PadronProcesarRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();

        try {
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setData(revaluacionService.listarRevaluacionSeleccionda(parametros));
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "listarevalporpadron")
    public ResponseEntity<?> listaRevaluacionePorPadron(@RequestBody PadronBuscarRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        responseAppBean.setData(revaluacionService.buscarRevaluacionesPadron(parametros));
        try {
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "listmorevseleccionadas")
    public ResponseEntity<?> listaRevaluacionesMoSeleccionadas(@RequestBody PadronProcesarMoRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setData(revaluacionService.listarMoRevaluacionSeleccionda(parametros));
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
