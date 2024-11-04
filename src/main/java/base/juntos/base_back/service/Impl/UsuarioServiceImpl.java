package base.juntos.base_back.service.Impl;

import base.juntos.base_back.dto.request.UsuarioRequest;
import base.juntos.base_back.model.Usuario;
import base.juntos.base_back.repository.UsuarioRepository;
import base.juntos.base_back.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> informacionUsuario(UsuarioRequest parametros) {
        return usuarioRepository.informacionUsuario(parametros);
    }
}
