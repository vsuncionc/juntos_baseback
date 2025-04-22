package base.juntos.base_back.api;


import base.juntos.base_back.model.CombosGenerico;
import base.juntos.base_back.model.ResponseAppBean;
import base.juntos.base_back.service.GenericoService;
import base.juntos.base_back.util.Constantes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping(value = "/genericos")
@RequiredArgsConstructor
public class GenericosController {

    private final GenericoService genericoService;

    //LISTA DE COMBOS DINAMICO
    @GetMapping(value = "listarcombo")
    public ResponseEntity<?> listalistaGrupoEsquema(@RequestParam String parametro) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setData(genericoService.listaGrupoEsquema(parametro));
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



    @GetMapping(value = "listarcomboperiodo")
    public ResponseEntity<?> listarcomboperiodo() {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            responseAppBean.setData(genericoService.listaPeriodo());
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

}
