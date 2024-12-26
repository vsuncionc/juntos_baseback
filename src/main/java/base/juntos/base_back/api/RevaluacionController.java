package base.juntos.base_back.api;

import base.juntos.base_back.convert.RevaluacionDtoConvert;
import base.juntos.base_back.dto.request.PadronRequest;
import base.juntos.base_back.dto.request.RevaluacionRequest;
import base.juntos.base_back.model.ResponseAppBean;
import base.juntos.base_back.service.RevaluacionService;
import base.juntos.base_back.util.Constantes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/revaluacion")
@RequiredArgsConstructor
public class RevaluacionController {

    private final RevaluacionService revaluacionService;
    private final RevaluacionDtoConvert revaluacionDtoConvert;

    @PostMapping(value ="buscar")
    public ResponseEntity<?> infoUsuario(@RequestBody RevaluacionRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setData(revaluacionDtoConvert.convertRevaluacionDto(revaluacionService.buscarRevaluaciones(parametros)));
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
            responseAppBean.setData(revaluacionDtoConvert.convertRevaluacionMhDto(revaluacionService.buscarMhRevaluacion(parametros)));
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
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "buscarPadron")
    public ResponseEntity<?> buscarPadron(@RequestBody PadronRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {

            responseAppBean.setData(revaluacionDtoConvert.convertRevaluacionPadronDto(revaluacionService.buscarRevaluacionesPadron(parametros)));
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
