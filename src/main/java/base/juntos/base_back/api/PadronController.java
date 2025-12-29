package base.juntos.base_back.api;

import base.juntos.base_back.convert.RevaluacionDtoConvert;
import base.juntos.base_back.dto.request.*;
import base.juntos.base_back.dto.response.GeneracionCierrePadronResponse;
import base.juntos.base_back.dto.response.GeneracionPadronResponse;
import base.juntos.base_back.dto.response.GeneracionPreCierreResponse; 
import base.juntos.base_back.model.InformacionCabeceraPreCierre;
import base.juntos.base_back.model.ResponseAppBean;
import base.juntos.base_back.service.GenerarExcelService;
import base.juntos.base_back.service.PadronService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/padron")
@RequiredArgsConstructor
public class PadronController {

    private final RevaluacionService revaluacionService;
    private final PadronService padronService;
    private final RevaluacionDtoConvert revaluacionDtoConvert;
    private final GenerarExcelService generarExcelService;

    @PostMapping(value = "buscarPadron")
    public ResponseEntity<?> buscarPadron(@RequestBody PadronBuscarRequest parametros) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {

            responseAppBean.setData(revaluacionDtoConvert.convertRevaluacionPadronDto(revaluacionService.buscarRevaluacionesPadron(parametros)));
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        } catch (Exception e) {
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "procesarPadron")
    public ResponseEntity<?> procesarPadron(@RequestBody PadronProcesarRequest parametros) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        String respuesta = "NOK";
        String mensaje = "";
        parametros.setCodigoUsuario("21582");
       // String codigopadron = "";
        List<GeneracionPadronResponse> codigopadron =  new ArrayList<>();
        try {
            Map<Integer, String> datos = padronService.ProcesarPadron(parametros);
            for (Map.Entry<Integer, String> info : datos.entrySet()) {
                log.info("clave=" + info.getKey() + ", valor=" + info.getValue());
                if (info.getKey() == 1) { // CODIGO PADRON
                   // codigopadron = info.getValue();
                    codigopadron.add(0,new GeneracionPadronResponse(Long.parseLong(info.getValue())));
                } else if (info.getKey() == 2) { // VALOR RESPUESTA
                    respuesta = info.getValue();
                } else if (info.getKey() == 3) { // TEXTO RESPUESTA
                    mensaje = info.getValue();
                }
            }

            if ("OK".equals(respuesta)) {
                responseAppBean.setData(codigopadron);
                responseAppBean.setStatus(Constantes.RESPONSE_OK);
                responseAppBean.setMessage(mensaje);
                responseAppBean.setCode(respuesta);
                responseAppBean.setToken("123456789");
                return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
            } else {
                responseAppBean.setStatus(Constantes.RESPONSE_OK);
                responseAppBean.setMessage(mensaje);
                responseAppBean.setCode(respuesta);
                responseAppBean.setToken("123456789");
                return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
            }

        } catch (Exception e) {
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "resumengeneracion")
    public ResponseEntity<?> resumenGeneracion(@RequestBody PadronBuscarRequest parametros) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setData(padronService.resumenPadronGenerado(parametros));
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        } catch (Exception e) {
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/generarexcelpadron")
    public ResponseEntity<?> generarExcelPadron(@RequestParam("id") Long id) throws IOException {
        PadronBuscarRequest parametros = new PadronBuscarRequest();
        parametros.setCodigoPadron(id);
        var resource = generarExcelService.listarHogaresGenPadron(parametros);
        String nombreArchivo = FechasUtilitarios.obtenerFechaHoraJunto();
        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+nombreArchivo)
                .body(resource);
    }

    @PostMapping(value = "buscarhogarespadron")
    public ResponseEntity<?> buscarHogaresPadron(@RequestBody PadronBuscarHogaresRequest parametros) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {

            responseAppBean.setData(padronService.listaHogaresPadron(parametros));
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        } catch (Exception e) {
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "listaselecthogarespadron")
    public ResponseEntity<?> listaSeleccionadaHogaresPadron(@RequestBody PadronSeleccionHogaresRequest parametros) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setData(padronService.listaHogaresSeleccionadosPorPadron(parametros));
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        } catch (Exception e) {
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "generarprecierre")
    public ResponseEntity<?> generarPrecierre(@RequestBody PadronSeleccionHogaresRequest parametros) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        String respuesta = "NOK";
        String mensaje = "";
        parametros.setCodigoUsuario("21582");
        List<GeneracionPreCierreResponse> codigoPreCierre =  new ArrayList<>();
        try {

            Map<Integer, String> datos = padronService.ProcesarPreCierre(parametros);
            for (Map.Entry<Integer, String> info : datos.entrySet()) {
                log.info("clave=" + info.getKey() + ", valor=" + info.getValue());
                if (info.getKey() == 1) { // CODIGO PADRON
                    mensaje=info.getValue();
                    respuesta = info.getValue();
                } else if (info.getKey() == 2) { // VALOR RESPUESTA
                    responseAppBean.setMessage(info.getValue());
                    codigoPreCierre.add(0,new GeneracionPreCierreResponse(Long.parseLong(info.getValue())));
                }
            }

            if ("OK".equals(respuesta)) {
                responseAppBean.setData(codigoPreCierre);
                responseAppBean.setStatus(Constantes.RESPONSE_OK);
                responseAppBean.setMessage(mensaje);
                responseAppBean.setCode(mensaje);
                responseAppBean.setToken("123456789");
                return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
            } else {
                responseAppBean.setStatus(Constantes.RESPONSE_OK);
                responseAppBean.setMessage(mensaje);
                responseAppBean.setCode(respuesta);
                responseAppBean.setToken("123456789");
                return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
            }

        } catch (Exception e) {
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "informacionprecierre")
    public ResponseEntity<?> informacionPrecierre(@RequestBody PadronPreCierreRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            List<InformacionCabeceraPreCierre> lista =padronService.informacionCabeceraPreCierre(parametros);
            responseAppBean.setData(lista);
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "listahogaresvalprecierre")
    public ResponseEntity<?> listaHogaresValidadosPreCierre(@RequestBody PadronPreCierreRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setData(padronService.lsHogaresAptosPreCierre(parametros));
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


    @PostMapping(value = "listahogaresusprecierre")
    public ResponseEntity<?> listaHogaresSuspendidosPreCierre(@RequestBody PadronPreCierreRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setData(padronService.lsHogaresSuspendidosPreCierre(parametros));
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


    @GetMapping(value = "lshogaresvalprecierrexel")
    public ResponseEntity<?> lsHogaresValPreCierre(@RequestParam("id") Long id) throws IOException{
         PadronPreCierreRequest parametros = new PadronPreCierreRequest();
        parametros.setCodigoPreValidacionHogar(id);
        var resource = generarExcelService.listaHogaresPreValidadosTodos(parametros);
        String nombreArchivo = FechasUtilitarios.obtenerFechaHoraJunto();
        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+nombreArchivo)
                .body(resource);
    }




    @PostMapping(value = "generarcierre")
    public ResponseEntity<?> generarCierre(@RequestBody PadronCierreRequest parametros) {
        List<GeneracionCierrePadronResponse> datos = new ArrayList<>();
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
           datos = padronService.ProcesarCierre(parametros);
           responseAppBean.setData(datos);
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        } catch (Exception e) {
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "listahogaresvalcierre")
    public ResponseEntity<?> listaHogaresValidadosCierre(@RequestBody PadronPreCierreRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setData(padronService.lsHogaresAptosCierre(parametros));
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


    @GetMapping(value = "listahogarsuspencierre")
    public ResponseEntity<?> listaHogaresSuspendidoCierre(@RequestBody PadronPreCierreRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setData(padronService.lsHogaresSuspendidosCierre(parametros));
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


    @GetMapping(value = "/generarexcelcierre")
    public ResponseEntity<?> generarExcelCierrePadron(@RequestParam("id") Long id) throws IOException {

        PadronPreCierreRequest   parametros = new PadronPreCierreRequest();
        parametros.setCodigoPreValidacionHogar(id);
         var resource = generarExcelService.listaHogaresAptos(parametros);
            String nombreArchivo = FechasUtilitarios.obtenerFechaHoraJuntoTablon("CIERRE");
     return  ResponseEntity.ok()
             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+nombreArchivo)
             .body(resource);
    }


}
