package com.base.base.api;

import com.base.base.convert.ColegioConvert;
import com.base.base.dto.request.ActActivodadColegioRequest;
import com.base.base.dto.request.ConsultaColegiosRequest;
import com.base.base.dto.request.RegistrarColegioPlanRequest;
import com.base.base.dto.response.InformacionArchivo;
import com.base.base.model.ActividadPlanColegio;
import com.base.base.model.Colegio;
import com.base.base.model.PlanAnualnstitucion;
import com.base.base.model.ResponseAppBean;
import com.base.base.service.ColegiosService;
import com.base.base.util.Constante;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/colegios")
@RequiredArgsConstructor
public class ColegiosController {

    private final ColegiosService colegiosService;
    private final ColegioConvert colegioConvert;

    @PostMapping(value = "listar", produces = "application/json")
    public ResponseEntity<?> listarColegiosUbigeo(@RequestBody ConsultaColegiosRequest consultaColegiosRequest,
                                                  HttpServletRequest request, HttpServletResponse response) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setData(colegioConvert.convertConsultaPlanAnualToDto(colegiosService.buscarColegios(consultaColegiosRequest)));
            responseAppBean.setStatus(Constante.RESPONSE_OK);
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(Constante.MENSAJE_ERROR);
            responseAppBean.setMessageBackend(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping(value = "registrarcolegioplan", produces = "application/json")
    public ResponseEntity<?> listarColegiosUbigeo(@RequestBody RegistrarColegioPlanRequest parametros, HttpServletRequest request, HttpServletResponse response) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            int codigoPlanColegio = colegiosService.insertarPlanColegio(parametros);
            responseAppBean.setStatus(Constante.RESPONSE_OK);
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(Constante.MENSAJE_ERROR);
            responseAppBean.setMessageBackend(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "actcolegioactividad", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizarActividadColegio(
            @RequestParam("codigodt") String codigo,
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam("codigocolegio") String codigocolegio,
            @RequestParam("codigoactividad") String codigoactividad,
            @RequestParam("codigoperiodo") String codigoperiodo,
            HttpServletRequest request, HttpServletResponse response) {
            ResponseAppBean responseAppBean = new ResponseAppBean();
            Map<String, String> respuesta =  new HashMap<String, String>();
            ActActivodadColegioRequest info = new ActActivodadColegioRequest();
        try {

            Date hoy = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String sFechaHora = formatter.format(hoy);

            String nombreArchivo = "COLEGIO_"+codigocolegio+"_"+codigoactividad+"_"+sFechaHora;
            info.setCodigodt(Integer.parseInt(codigo));
            info.setCodigoColegio(Integer.parseInt(codigocolegio));
            info.setCodigoActividad(Integer.parseInt(codigoactividad));
            info.setPeriodo(codigoperiodo);
            InformacionArchivo informacionArchivo = new InformacionArchivo(archivo);
            informacionArchivo.setNombreArchivo(nombreArchivo);
            info.setFormato(informacionArchivo.getExtension());
            if(informacionArchivo.getPeso()>0){
                respuesta =colegiosService.subirArchivo(informacionArchivo,info);
                for (Map.Entry<String, String> entry : respuesta.entrySet()) {
                    if(entry.getKey().equals("0")){
                        break;
                    }else{

                        responseAppBean.setStatus(Constante.RESPONSE_ERROR);
                        responseAppBean.setMessage(Constante.MENSAJE_ERROR);
                        responseAppBean.setMessageBackend(entry.getValue());
                        return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
                        //log.info("clave=" + entry.getKey() + ", valor=" + entry.getValue());
                    }

                }
            }
           //ACTUALIZAMOS LOS DATOS
            nombreArchivo = nombreArchivo+"."+info.getFormato();
            info.setNombreArchivo(nombreArchivo);
            colegiosService.actualizarActividad(info);
            responseAppBean.setStatus(Constante.RESPONSE_OK);
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(Constante.MENSAJE_ERROR);
            responseAppBean.setMessageBackend(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping(value = "actividadcolegio", produces = "application/json")
    public ResponseEntity<?> actividadesPorColegio(@RequestBody ActActivodadColegioRequest parametros, HttpServletRequest request, HttpServletResponse response) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setData(colegioConvert.convertActividadColegioDto(colegiosService.listaActividadPorColegio(parametros)));
            responseAppBean.setStatus(Constante.RESPONSE_OK);
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(Constante.MENSAJE_ERROR);
            responseAppBean.setMessageBackend(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "planescolegio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listarPlanesPorColegio(
            @RequestParam("codigocolegio") int codigocolegio,
            HttpServletRequest request, HttpServletResponse response) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setData(colegioConvert.convertListarPlanColegioDto(colegiosService.listarPlanesPorColegio(codigocolegio)));
           // List<PlanAnualnstitucion> lista= colegiosService.listarPlanesPorColegio(codigocolegio);
            //responseAppBean.setData(lista);
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
