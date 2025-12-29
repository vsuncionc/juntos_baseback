package base.juntos.base_back.api;

import base.juntos.base_back.dto.request.TablonBuscarRequest;
import base.juntos.base_back.model.ResponseAppBean;
import base.juntos.base_back.service.GenerarExcelService;
import base.juntos.base_back.service.TablonService;
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
@RequestMapping(value = "/tablon")
@RequiredArgsConstructor
public class TablonController {

    private final TablonService tablonService;
    private final GenerarExcelService generarExcelService;

    @PostMapping(value ="buscar")
    public ResponseEntity<?> infoRevaluacion(@RequestBody TablonBuscarRequest parametros){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setStatus(Constantes.RESPONSE_OK);
            responseAppBean.setData(tablonService.listaTablones(parametros));
            responseAppBean.setToken("123456789");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
        }catch (Exception e){
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/generarexceltablon")
    public ResponseEntity<?> generarExcelTablon(@RequestParam("id") Long id) throws IOException {
        var resource = generarExcelService.reporteTablon(id);
        String nombreArchivo = FechasUtilitarios.obtenerFechaHoraJuntoTablon("REPORTE_HOGARES_APTOS");
        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+nombreArchivo)
                .body(resource);
    }
}
