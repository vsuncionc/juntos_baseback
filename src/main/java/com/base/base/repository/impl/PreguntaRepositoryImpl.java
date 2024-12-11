package com.base.base.repository.impl;

import com.base.base.dto.request.PreguntaRequest;
import com.base.base.repository.PreguntaRepository;
import com.base.base.util.Constante;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PreguntaRepositoryImpl implements PreguntaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public int crearPregunta(PreguntaRequest parametros) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO "+ Constante.ESQUEMA+".tdpregunta "+
        "(EVALUACION_ID,CDESCRIPCION,FCREACION,NESTADO)"+
        "VALUES(:P_EVALUACION_ID,:P_DESCRIPCION,CURRENT_TIMESTAMP,:P_ESTADO)");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("P_EVALUACION_ID",parametros.getCodigoevaluacion());
        params.addValue("P_DESCRIPCION",parametros.getNombrepregunta());
        params.addValue("P_ESTADO",Constante.ESTADO_ACTIVO);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql.toString(), params, keyHolder, new String[]{"PREGUNTA_PK"});
        log.info("---------"+keyHolder.getKey().intValue());
        return keyHolder.getKey().intValue();
    }
}
