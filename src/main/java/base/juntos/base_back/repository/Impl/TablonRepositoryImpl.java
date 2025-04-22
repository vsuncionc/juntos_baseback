package base.juntos.base_back.repository.Impl;

import base.juntos.base_back.dto.request.TablonBuscarRequest;
import base.juntos.base_back.model.HogaresTablonExcel;
import base.juntos.base_back.model.HogaresValidadosAptosExcel;
import base.juntos.base_back.model.ResultadoRevaluacion;
import base.juntos.base_back.model.TablonCierrePadron;
import base.juntos.base_back.repository.TablonRepository;
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
public class TablonRepositoryImpl implements TablonRepository {

    private final EntityManager entityManager;

    private String nombreEsquema ="SITC";


    @Override
    public List<TablonCierrePadron> listaTablones(TablonBuscarRequest parametros) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_TABLONES", TablonCierrePadron.class)
                .registerStoredProcedureParameter("C_TABLONES",Class.class, ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("POPCION",String.class,ParameterMode.IN)
                .registerStoredProcedureParameter("PCRITERIO",String.class,ParameterMode.IN)
                .registerStoredProcedureParameter("PFGENERACION",String.class,ParameterMode.IN)
                .setParameter("POPCION",parametros.getOpcionBusqueda())
                .setParameter("PCRITERIO",parametros.getCriterio())
                .setParameter("PFGENERACION",parametros.getFechaProcesamiento());
        sp.execute();
        return sp.getResultList();
    }

    @Override
    public List<HogaresTablonExcel> reporteTablon(Long id) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_EXCELTABLON", HogaresTablonExcel.class)
                .registerStoredProcedureParameter("C_HGAPTOS",Class.class,ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_ID_PREVALHOGAR",Long.class,ParameterMode.IN)
                .setParameter("P_ID_PREVALHOGAR",id);
        sp.execute();
        return sp.getResultList();
    }
}
