package base.juntos.base_back.auth;

import base.juntos.base_back.dto.request.UsuarioLogin;
import base.juntos.base_back.dto.response.UsuarioLogeadoResponse;
import base.juntos.base_back.model.ResponseAppBean;
import base.juntos.base_back.util.Constantes;
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
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class authController {

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLogin parametros) {
        ResponseAppBean responseAppBean = new ResponseAppBean();
        try {
            if(parametros.getUsername().equals("VSUNCION")){
                UsuarioLogeadoResponse info = new UsuarioLogeadoResponse();
                info.setNombre("VLADIMIR SUNCION");
                responseAppBean.setData(info);
                responseAppBean.setStatus(Constantes.RESPONSE_OK);
                responseAppBean.setToken("123456789");
                return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
            }else{
                responseAppBean.setData("error");
                responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
                return new ResponseEntity<>(responseAppBean, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            responseAppBean.setStatus(Constantes.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            responseAppBean.setCode(e.getLocalizedMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
