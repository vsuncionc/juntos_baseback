package base.juntos.base_back.repository.Impl;

import base.juntos.base_back.dto.request.PadronRequest;
import base.juntos.base_back.dto.request.RevaluacionRequest;
import base.juntos.base_back.model.ResultadoRevaluacion;
import base.juntos.base_back.model.RevaluacionMiembrObjetivo;
import base.juntos.base_back.model.RevaluacionMimebroHogar;
import base.juntos.base_back.model.RevaluacionPadron;
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
    public List<ResultadoRevaluacion> buscarRevaluaciones(RevaluacionRequest parametros) {
        var sp = entityManager
               .createStoredProcedureQuery(this.nombreEsquema+".PKG_TIM.TIMSP_BUSCAREVISIONPOST", ResultadoRevaluacion.class)
               .registerStoredProcedureParameter("C_LISTA_REVALUACIONES",Class.class, ParameterMode.REF_CURSOR)
               .registerStoredProcedureParameter("P_EXPEDIENTE",String.class,ParameterMode.IN)
               .registerStoredProcedureParameter("P_X_DOCUMENTO",String.class,ParameterMode.IN)
               .registerStoredProcedureParameter("P_X_DESCRIPCION",String.class,ParameterMode.IN)
                .registerStoredProcedureParameter("P_GRUPOESQUEMA",Integer.class,ParameterMode.IN)
               .setParameter("P_EXPEDIENTE",parametros.getExpediente())
               .setParameter("P_X_DOCUMENTO",parametros.getDocumento())
               .setParameter("P_X_DESCRIPCION",parametros.getDescripcion())
                .setParameter("P_GRUPOESQUEMA", parametros.getGrupoesquema());
             sp.execute();
        return sp.getResultList();

    }

    @Override
    public List<RevaluacionMimebroHogar> buscarMhRevaluacion(RevaluacionRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(this.nombreEsquema+".PKG_TIM.SP_MHXREVALUACION", RevaluacionMimebroHogar.class)
                .registerStoredProcedureParameter("C_LISTA_REVALUACIONES_MO",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("PIDREEVALUACION",Integer.class,ParameterMode.IN)
                .setParameter("PIDREEVALUACION",parametros.getCodigoRevaluacion());
        sp.execute();
        List<RevaluacionMimebroHogar> lista = sp.getResultList();
        return lista;
    }

    @Override
    public List<RevaluacionMiembrObjetivo> buscarMoRevaluacion(RevaluacionRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(this.nombreEsquema+".PKG_TIM.SP_MOXREVALUACION", RevaluacionMiembrObjetivo.class)
                .registerStoredProcedureParameter("C_LISTA_REVALUACIONES_MO",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("PIDREEVALUACION",Integer.class,ParameterMode.IN)
                .setParameter("PIDREEVALUACION",parametros.getCodigoRevaluacion());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<RevaluacionPadron> buscarRevaluacionesPadron(PadronRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(this.nombreEsquema+".PKG_TIM.TIMSP_BUSCARREVPADRON", RevaluacionPadron.class)
                .registerStoredProcedureParameter("C_LISTA_REVALUACIONES",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_CODPADRONPAGO",Integer.class,ParameterMode.IN)
                .setParameter("P_CODPADRONPAGO",parametros.getCodigoPadron());
            sp.execute();
        return sp.getResultList();
    }
}
