package base.juntos.base_back.repository.Impl;

import base.juntos.base_back.model.ComboGenericoNum;
import base.juntos.base_back.model.CombosGenerico;
import base.juntos.base_back.repository.GenericoRepository;
import base.juntos.base_back.util.Constantes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class GenericoRepositoryImpl implements GenericoRepository {

    private final EntityManager entityManager;

    @Override
    public List<CombosGenerico> listaGrupoEsquema(String parametro) {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_LISGROUPESQ", CombosGenerico.class )
                .registerStoredProcedureParameter("C_GRPESQ",Class.class, ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter("P_VALOR",String.class, ParameterMode.IN)
                .setParameter("P_VALOR",parametro);
             sp.execute() ;
        return sp.getResultList();
    }

    @Override
    public List<ComboGenericoNum> listaPeriodo() {
        var sp = entityManager
                .createStoredProcedureQuery(Constantes.ESQUEMA_SITC +"."+Constantes.PAQUETE_TIM_REVPOST+".TTIM_PERIODOS", ComboGenericoNum.class )
                .registerStoredProcedureParameter("C_PADRONES",Class.class, ParameterMode.REF_CURSOR);
        sp.execute() ;
        return sp.getResultList();
    }
}
