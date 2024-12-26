package base.juntos.base_back.service;

import base.juntos.base_back.dto.request.UsuarioRequest;
import base.juntos.base_back.model.Usuario;

import java.util.Optional;

public interface UsuarioService {
   Optional<Usuario> informacionUsuario(UsuarioRequest parametros);
}
