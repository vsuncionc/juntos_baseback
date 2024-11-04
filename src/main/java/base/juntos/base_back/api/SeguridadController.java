package base.juntos.base_back.api;

import base.juntos.base_back.dto.request.UsuarioRequest;

import base.juntos.base_back.model.ResponseAppBean;
import base.juntos.base_back.model.Usuario;
import base.juntos.base_back.service.UsuarioService;
import base.juntos.base_back.util.Constantes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/seguridad")
@RequiredArgsConstructor
public class SeguridadController {

    private final UsuarioService usuarioService;

    @GetMapping("/perfilusuario")
    public void index()  {
        log.info("Ingreso la pagina princiapl");
    }

   @PostMapping("/busqueda")
    public ResponseEntity<?> infoUsuario(@RequestBody UsuarioRequest parametros){
       ResponseAppBean responseAppBean = new ResponseAppBean();
       try {
        Optional<Usuario> infomracion = usuarioService.informacionUsuario(parametros);
         if (infomracion.isEmpty()){
             log.error("sin ");
         }
           responseAppBean.setStatus(Constantes.RESPONSE_OK);
           responseAppBean.setData(infomracion.get());
           return new ResponseEntity<>(responseAppBean, HttpStatus.OK);

       }catch (Exception e){
           responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
           responseAppBean.setMessage(e.getMessage());
           responseAppBean.setCode(e.getLocalizedMessage());
           return new ResponseEntity<>(responseAppBean, HttpStatus.ACCEPTED);
       }
    }
}
