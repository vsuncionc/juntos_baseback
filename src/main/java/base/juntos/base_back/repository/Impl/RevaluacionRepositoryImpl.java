package base.juntos.base_back.repository.Impl;

import base.juntos.base_back.dto.request.PadronBuscarRequest;
import base.juntos.base_back.dto.request.PadronProcesarMoRequest;
import base.juntos.base_back.dto.request.PadronProcesarRequest;
import base.juntos.base_back.dto.request.RevaluacionRequest;
import base.juntos.base_back.model.*;
import base.juntos.base_back.repository.RevaluacionRepository;
import base.juntos.base_back.util.Constantes;
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
    public RevaluacionInformacion infoRevaluacion(RevaluacionRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_INFREVALUACION", RevaluacionInformacion.class)
                .registerStoredProcedureParameter("C_INFREV",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("PIDREVALUACION",Long.class,ParameterMode.IN)
                .setParameter("PIDREVALUACION",parametros.getCodigoRevaluacion());
        sp.execute();
        List<RevaluacionInformacion> lista = sp.getResultList();
        return lista.get(0);
    }

    @Override
    public List<ResultadoRevaluacion> buscarRevaluaciones(RevaluacionRequest parametros) {
        var sp = entityManager
               .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TIMSP_BUSCAREVISIONPOST", ResultadoRevaluacion.class)
               .registerStoredProcedureParameter("C_LISTA_REVALUACIONES",Class.class, ParameterMode.REF_CURSOR)
               .registerStoredProcedureParameter("P_TPBUSQEDA",String.class,ParameterMode.IN)
               .registerStoredProcedureParameter("P_CRITERIO",String.class,ParameterMode.IN)
               .registerStoredProcedureParameter("P_GRUPOESQUEMA",String.class,ParameterMode.IN)
               .setParameter("P_TPBUSQEDA",parametros.getTipobusqueda())
               .setParameter("P_CRITERIO",parametros.getCriterio())
               .setParameter("P_GRUPOESQUEMA",parametros.getGrupoesquema());
             sp.execute();
        return sp.getResultList();

    }

    @Override
    public List<RevaluacionMimebroHogar> buscarMhRevaluacion(RevaluacionRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".SP_MHXREVALUACION", RevaluacionMimebroHogar.class)
                .registerStoredProcedureParameter("C_LISTA_REVALUACIONES_MO",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("PIDREEVALUACION",Long.class,ParameterMode.IN)
                .setParameter("PIDREEVALUACION",parametros.getCodigoRevaluacion());
        sp.execute();
        List<RevaluacionMimebroHogar> lista = sp.getResultList();
        return lista;
    }

    @Override
    public List<RevaluacionMiembrObjetivo> buscarMoRevaluacion(RevaluacionRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".SP_MOXREVALUACION", RevaluacionMiembrObjetivo.class)
                .registerStoredProcedureParameter("C_LISTA_REVALUACIONES_MO",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("PIDREEVALUACION",Long.class,ParameterMode.IN)
                .setParameter("PIDREEVALUACION",parametros.getCodigoRevaluacion());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<RevaluacionPadron> buscarRevaluacionesPadron(PadronBuscarRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TIMSP_BUSCARREVPADRON", RevaluacionPadron.class)
                .registerStoredProcedureParameter("C_LISTA_REVALUACIONES",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_CODPADRONPAGO",Integer.class,ParameterMode.IN)
                .setParameter("P_CODPADRONPAGO",parametros.getCodigoPadron());
            sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<RevaluacionSeleccionada> listarRevaluacionSeleccionda(PadronProcesarRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_SELECREVISON", RevaluacionSeleccionada.class)
                .registerStoredProcedureParameter("C_REVSELEC",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("PIDREVALUACIONES",String.class,ParameterMode.IN)
                .setParameter("PIDREVALUACIONES",parametros.getCodigoRevaluacion());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<RevaluacionMiembrObjetivoSelec> listarMoRevaluacionSeleccionda(PadronProcesarMoRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_SELECREVISONMO", RevaluacionMiembrObjetivoSelec.class)
                .registerStoredProcedureParameter("C_REVSELEC",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("PIDREVALUACIONES",String.class,ParameterMode.IN)
                .setParameter("PIDREVALUACIONES",parametros.getCodigoRevaluacion());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<HogaresExcelGenPadron> listaHogaresGneradosPadron(PadronBuscarRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_EXCELPROCPADRON", HogaresExcelGenPadron.class)
                .registerStoredProcedureParameter("C_HGARES",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_CODPADRONPAGO",String.class,ParameterMode.IN)
                .setParameter("P_CODPADRONPAGO",parametros.getCodigoPadron());
        sp.execute();
        return sp.getResultList();
    }


}
