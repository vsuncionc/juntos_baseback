package com.base.base.repository.impl;
import com.base.base.dto.request.ConsultaPlanAnualRequest;
import com.base.base.model.PlanAnual;
import com.base.base.repository.PlanAnualRepository;
import com.base.base.util.Constante;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PlanAnualRepositoryimpl  implements PlanAnualRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<PlanAnual> buscarPlanes(ConsultaPlanAnualRequest consultaGenerico) {
        StringBuilder sql = new StringBuilder();
        List<PlanAnual> lista;
        String filtro= "";
        MapSqlParameterSource params = new MapSqlParameterSource();

            if(consultaGenerico.getPeriodo()!=null && !consultaGenerico.getPeriodo().isEmpty()){
                filtro ="AND CPERIODO= :P_CPERIODO";
                params.addValue("P_CPERIODO",consultaGenerico.getPeriodo());
            }
            if(consultaGenerico.getTitulo()!=null && !consultaGenerico.getTitulo().isEmpty()){
                filtro = filtro +" OR UPPER(CTITULO) LIKE '%"+consultaGenerico.getTitulo().trim()+"%'";
                params.addValue("P_CTITULO",consultaGenerico.getTitulo());
            }
            if(consultaGenerico.getDescripcion()!=null && !consultaGenerico.getDescripcion().isEmpty()){
                filtro = filtro +" OR UPPER(CDESCRIPCION) LIKE '%"+consultaGenerico.getDescripcion().trim()+"%' ";
                params.addValue("P_CDESCRIPCION",consultaGenerico.getDescripcion());
            }
            sql.append(
                "SELECT " +
                       "PLANANULA_PK plananual,CTITULO, CDESCRIPCION, FCREACION, CPERIODO, CANIO, " +
                       "CMES, NCANTPREGUNTAS, NESTADO  FROM "+
                      Constante.ESQUEMA +".tcplananual WHERE 1=1 "+
                      filtro);
            lista = namedParameterJdbcTemplate.query(sql.toString(),params, BeanPropertyRowMapper.newInstance(PlanAnual.class));

        return lista;
    }

    @Override
    public void insertarPlan(ConsultaPlanAnualRequest consultaGenerico) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO "+Constante.ESQUEMA+".tcplananual"+
                "(CTITULO,CDESCRIPCION,FCREACION,CPERIODO,CANIO,CMES,NCANTPREGUNTAS,NESTADO) " +
                " VALUES(:P_TITULO,:P_CDESCRIPCION,CURRENT_TIMESTAMP,:P_CPERIODO,:P_CANIO,:P_CMES,:P_NCANTPREGUNTAS,:P_ESTADO)");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("P_TITULO", consultaGenerico.getTitulo());
        params.addValue("P_CDESCRIPCION", consultaGenerico.getDescripcion());
        params.addValue("P_CPERIODO", consultaGenerico.getPeriodo());
        params.addValue("P_CANIO", consultaGenerico.getAnio());
        params.addValue("P_CMES", consultaGenerico.getMes());
        params.addValue("P_NCANTPREGUNTAS", consultaGenerico.getNumeroPreguntas());
        params.addValue("P_ESTADO", Constante.ESTADO_ACTIVO);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql.toString(), params, keyHolder, new String[]{"PLANANULA_PK"});
        log.info("---------"+keyHolder.getKey().intValue());
    }
}
