package base.juntos.base_back.repository;

import base.juntos.base_back.dto.request.UsuarioRequest;
import base.juntos.base_back.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> informacionUsuario(UsuarioRequest parametros);
}
