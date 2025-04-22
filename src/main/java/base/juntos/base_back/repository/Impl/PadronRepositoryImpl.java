package base.juntos.base_back.repository.Impl;

import base.juntos.base_back.dto.request.*;
import base.juntos.base_back.dto.response.GeneracionCierrePadronResponse;
import base.juntos.base_back.model.*;
import base.juntos.base_back.repository.PadronRepository;
import base.juntos.base_back.util.Constantes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PadronRepositoryImpl implements PadronRepository {

    private final EntityManager entityManager;


    @Override
    public Map<Integer, String> ProcesarPadron(PadronProcesarRequest parametros) {
        Map<Integer, String> datos = new HashMap<Integer, String>();
         var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TIMSP_GENPADRONREVPOS")
                 .registerStoredProcedureParameter("PIDREVALUACIONES",String.class, ParameterMode.IN)
                 .registerStoredProcedureParameter("P_NOMPADRON",String.class,ParameterMode.IN)
                 .registerStoredProcedureParameter("P_USUARIO",String.class,ParameterMode.IN)
                 .registerStoredProcedureParameter("P_CODPADRON",String.class,ParameterMode.OUT)
                 .registerStoredProcedureParameter("P_RPTA",String.class,ParameterMode.OUT)
                 .registerStoredProcedureParameter("P_MENSAJE",String.class,ParameterMode.OUT)

                  .setParameter("PIDREVALUACIONES",parametros.getCodigoRevaluacion())
                  .setParameter("P_NOMPADRON",parametros.getDescripcion())
                  .setParameter("P_USUARIO",parametros.getCodigoUsuario());
             sp.execute();
              datos.put(1, sp.getOutputParameterValue("P_CODPADRON").toString());
              datos.put(2, sp.getOutputParameterValue("P_RPTA").toString());
              datos.put(3, sp.getOutputParameterValue("P_MENSAJE").toString());
        return datos;
    }

    @Override
    public List<ResumenGeneracionPadron> resumenPadronGenerado(PadronBuscarRequest parametros) {
        var sp= entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_RESUMENGENPADROREV", ResumenGeneracionPadron.class)
                .registerStoredProcedureParameter("C_HGPADRON",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("PCODPADRON",Long.class,ParameterMode.IN)
                .setParameter("PCODPADRON",parametros.getCodigoPadron());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<HogaresPadronRevaluacion> listaHogaresPadron(PadronBuscarHogaresRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_LISTHOGREVPOST", HogaresPadronRevaluacion.class)
                .registerStoredProcedureParameter("C_TIM",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_TIPOESQUEMA",String.class,ParameterMode.IN)
                .registerStoredProcedureParameter("P_CODPERIDO",String.class,ParameterMode.IN)
                .registerStoredProcedureParameter("P_CODIGOHOGAR",Long.class,ParameterMode.IN)
                .registerStoredProcedureParameter("P_FPADRONPAGO",String.class,ParameterMode.IN)
                .registerStoredProcedureParameter("P_DESCRIPCION",String.class,ParameterMode.IN)
                .setParameter("P_TIPOESQUEMA",parametros.getTipoEsquema())
                .setParameter("P_CODPERIDO",parametros.getCodigoPeriodo())
                .setParameter("P_CODIGOHOGAR",parametros.getCodigoHogar())
                .setParameter("P_FPADRONPAGO",parametros.getFechaPadron())
                .setParameter("P_DESCRIPCION",parametros.getDescripcionPadron());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<HogaresPadronRevaluacion> listaHogaresSeleccionadosPorPadron(PadronSeleccionHogaresRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_SELECPADRONREVISON", HogaresPadronRevaluacion.class)
                .registerStoredProcedureParameter("C_PADRONSELEC",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("PIDTPADRONES",String.class,ParameterMode.IN)
                .setParameter("PIDTPADRONES",parametros.getCodigoDetPadron());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public Map<Integer,String> ProcesarPreCierre(PadronSeleccionHogaresRequest parametros) {
        Map<Integer, String> datos = new HashMap<Integer, String>();
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_PREVALHOGAR")
                .registerStoredProcedureParameter("PSECPDPAGODETALLEPEND",String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("P_USUARIO",String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("PRPTA",String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("CODIGO_PRECIERRE",String.class, ParameterMode.OUT)

                .setParameter("PSECPDPAGODETALLEPEND",parametros.getCodigoDetPadron())
                .setParameter("P_USUARIO",parametros.getCodigoUsuario());
             sp.execute();
         datos.put(1, sp.getOutputParameterValue("PRPTA").toString());
         datos.put(2, sp.getOutputParameterValue("CODIGO_PRECIERRE").toString());
        return datos;
    }

    @Override
    public List<HogaresPreValidadosAptos> lsHogaresAptosPreCierre(PadronPreCierreRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_TMP_HGAPTOSVALIDADOS", HogaresPreValidadosAptos.class)
                .registerStoredProcedureParameter("C_HGAPTOS",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_ID_PREVALHOGAR",Long.class,ParameterMode.IN)
                .setParameter("P_ID_PREVALHOGAR",parametros.getCodigoPreValidacionHogar());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<HogaresPreValidadoSuspendido> lsHogaresSuspendidosPreCierre(PadronPreCierreRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_TMP_HGSUSPENDIDOS", HogaresPreValidadoSuspendido.class)
                .registerStoredProcedureParameter("C_HGAPTOS",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_ID_PREVALHOGAR",Long.class,ParameterMode.IN)
                .setParameter("P_ID_PREVALHOGAR",parametros.getCodigoPreValidacionHogar());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<GeneracionCierrePadronResponse>  ProcesarCierre(PadronCierreRequest parametros) {
        List<GeneracionCierrePadronResponse> datos = new ArrayList<>();
        GeneracionCierrePadronResponse info = new GeneracionCierrePadronResponse();
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_CIERREVALHOGAR")
                .registerStoredProcedureParameter("PID_PREVALHOGAR",Number.class, ParameterMode.IN)
                .registerStoredProcedureParameter("PRPTA",String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("PMENSAJE",String.class, ParameterMode.OUT)
                .setParameter("PID_PREVALHOGAR",parametros.getCodigoPreValidacionHogar());
        sp.execute();
        info.setRespuesta(sp.getOutputParameterValue("PRPTA").toString());
        info.setMensaje(sp.getOutputParameterValue("PMENSAJE").toString());
        datos.add(info);
        return datos;
    }

    @Override
    public List<InformacionCabeceraPreCierre> informacionCabeceraPreCierre(PadronPreCierreRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_INFCABCIERRE", InformacionCabeceraPreCierre.class)
                .registerStoredProcedureParameter("C_INFOCAB",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_ID_PREVALHOGAR",Long.class,ParameterMode.IN)
                .setParameter("P_ID_PREVALHOGAR",parametros.getCodigoPreValidacionHogar());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<HogaresValidadosAptos> lsHogaresAptosCierre(PadronPreCierreRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_HGAPTOSVALIDADOS", HogaresValidadosAptos.class)
                .registerStoredProcedureParameter("C_HGAPTOS",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_ID_PREVALHOGAR",Long.class,ParameterMode.IN)
                .setParameter("P_ID_PREVALHOGAR",parametros.getCodigoPreValidacionHogar());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<HogaresValidadoSuspendido> lsHogaresSuspendidosCierre(PadronPreCierreRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_HGSUSPENDIDOS", HogaresValidadoSuspendido.class)
                .registerStoredProcedureParameter("C_HGAPTOS",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_ID_PREVALHOGAR",Long.class,ParameterMode.IN)
                .setParameter("P_ID_PREVALHOGAR",parametros.getCodigoPreValidacionHogar());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<HogaresValidadosAptosExcel> lsHogaresExcelAptosCierre(PadronPreCierreRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_EXCELCIERRE", HogaresValidadosAptosExcel.class)
                .registerStoredProcedureParameter("C_HGAPTOS",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_ID_PREVALHOGAR",Long.class,ParameterMode.IN)
                .setParameter("P_ID_PREVALHOGAR",parametros.getCodigoPreValidacionHogar());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<HogaresValPreCierreTodos> lsHogaresValPreCierreExcel(PadronPreCierreRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_HOGARPREVALREPORTE", HogaresValPreCierreTodos.class)
                .registerStoredProcedureParameter("C_HOGARESVALPRECIERRE",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("PID_PREVALHOGAR",Long.class,ParameterMode.IN)
                .setParameter("PID_PREVALHOGAR",parametros.getCodigoPreValidacionHogar());
        sp.execute();
        return sp.getResultList();
    }
}
