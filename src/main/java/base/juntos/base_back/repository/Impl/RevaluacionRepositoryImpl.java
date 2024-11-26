package base.juntos.base_back.repository.Impl;

import base.juntos.base_back.dto.request.RevaluacionRequest;
import base.juntos.base_back.model.BusquedaRevaluacion;
import base.juntos.base_back.model.Revaluacion;
import base.juntos.base_back.repository.RevaluacionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RevaluacionRepositoryImpl implements RevaluacionRepository {

    private final EntityManager entityManager;

    private String nombreEsquema ="SITC";

    @Override
    public List<BusquedaRevaluacion> buscarRevaluaciones(RevaluacionRequest parametros) {
        var sp = entityManager
               .createStoredProcedureQuery(this.nombreEsquema+".PKG_TIM.TIMSP_BUSCAREVISIONPOST", BusquedaRevaluacion.class)
               .registerStoredProcedureParameter("C_LISTA_REVALUACIONES",Class.class, ParameterMode.REF_CURSOR)
               .registerStoredProcedureParameter("P_EXPEDIENTE",String.class,ParameterMode.IN)
               .registerStoredProcedureParameter("P_X_DOCUMENTO",String.class,ParameterMode.IN)
               .registerStoredProcedureParameter("P_X_DESCRIPCION",String.class,ParameterMode.IN)
               .setParameter("P_EXPEDIENTE",parametros.getExpediente())
               .setParameter("P_X_DOCUMENTO",parametros.getDocumento())
               .setParameter("P_X_DESCRIPCION",parametros.getDescripcion());
             sp.execute();
        return sp.getResultList();

    }
}
