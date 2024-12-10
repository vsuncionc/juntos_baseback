package com.base.base.repository.impl;


import com.base.base.dto.request.EvaluacionRequest;
import com.base.base.repository.EvaluacionRepository;
import com.base.base.util.Constante;
import com.base.base.util.Fechas;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EvaluacionRepositoryImpl implements EvaluacionRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public int RegistrarEvaluacion(EvaluacionRequest parametros) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO "+ Constante.ESQUEMA+".tcevaluacion(" +
          "CTITULO,CDESCRIPCION,FCREACION,FAPERTURA, " +
          "FCIERRE,NDURACION,CPERIODO,CANIO,NCANTPREGUNTAS,NESTADO,CUSUCREA,CUSUACT) " +
          "VALUES(:P_CTITULO,:P_CDESCRIPCION,CURRENT_TIMESTAMP,:P_FAPERTURA, " +
          ":P_FCIERRE,:P_NDURACION,:P_CPERIODO,:P_CANIO,:P_NCANTPREGUNTAS, " +
          ":P_NESTADO,:P_CUSUCREA,:P_CUSUACT)");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("P_CTITULO",parametros.getNombre());
        params.addValue("P_CDESCRIPCION",parametros.getDescripcion());
        params.addValue("P_FAPERTURA", parametros.getFechApertura());
        params.addValue("P_FCIERRE",parametros.getFechaCierre());
        params.addValue("P_NDURACION",parametros.getTiempoDuracion());
        params.addValue("P_CPERIODO",parametros.getPeriodo());
        params.addValue("P_CANIO",parametros.getAnio());
        params.addValue("P_NCANTPREGUNTAS",parametros.getCantidadPreguntas());
        params.addValue("P_NESTADO",Constante.ESTADO_ACTIVO);
        params.addValue("P_CUSUCREA",null);
        params.addValue("P_CUSUACT",null);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql.toString(), params, keyHolder, new String[]{"EVALUACION_PK"});
        log.info("---------"+keyHolder.getKey().intValue());

      return keyHolder.getKey().intValue();
    }
}
