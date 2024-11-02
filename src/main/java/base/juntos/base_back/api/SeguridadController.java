package base.juntos.base_back.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/seguridad")
@RequiredArgsConstructor
public class SeguridadController {

    @GetMapping("/perfilusuario")
    public void index()  {
        log.info("Ingreso la pagina princiapl");
    }
}
