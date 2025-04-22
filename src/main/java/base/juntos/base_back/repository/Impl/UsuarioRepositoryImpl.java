package base.juntos.base_back.repository.Impl;

import base.juntos.base_back.dto.request.UsuarioRequest;
import base.juntos.base_back.model.Usuario;
import base.juntos.base_back.repository.UsuarioRepository;
import base.juntos.base_back.util.Constantes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final EntityManager entityManager;


    @Override
    public Optional<Usuario> informacionUsuario(UsuarioRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SEGURIDAD+"."+Constantes.PAQUETE_SEGURIDAD+".PINFOUSUARIO",Usuario.class)
                .registerStoredProcedureParameter("P_OUT_CURSOR", Class.class, ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_IN_USUARIO", String.class,ParameterMode.IN)
                .setParameter("P_IN_USUARIO",parametros.getUsuario());
        try {
            sp.execute();
        }catch (Exception e){
            log.error("listaUsuariosGel ",e.getMessage());
        } finally {
            entityManager.close();
        }
        Usuario info = (Usuario) sp.getResultList().get(0);
        return Optional.ofNullable(info);
    }
}
