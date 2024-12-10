package com.base.base.repository.impl;

import com.base.base.dto.request.ConsultActividadRequest;
import com.base.base.dto.request.RegistroPlanAnualActividad;
import com.base.base.model.Actividad;
import com.base.base.model.Colegio;
import com.base.base.repository.ActividadRepository;
import com.base.base.util.Constante;
import com.base.base.util.Fechas;
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

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ActividadRepositoryImpl implements ActividadRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<Actividad> busquedaActividad(ConsultActividadRequest parametros) {
        StringBuilder sql = new StringBuilder();
        List<Actividad> lista;
        String filtro= "";
        MapSqlParameterSource params = new MapSqlParameterSource();

        if(parametros.getCodigoPlanAnual()>0){
            filtro =" AND PA.PLANANULA_PK = :P_PLANANULA_PK";
            params.addValue("P_PLANANULA_PK", parametros.getCodigoPlanAnual());
        }
        if(parametros.getNombrePlanAnual()!=null && !parametros.getNombrePlanAnual().isEmpty()){
            filtro =" OR UPPER(PA.CTITULO) LIKE LIKE '%"+ parametros.getNombrePlanAnual()+"%'";
        }
        if(parametros.getDescripcionActividad()!=null && !parametros.getDescripcionActividad().isEmpty()){
            filtro =" OR UPPER(ACT.CDESCRIPCION) LIKE LIKE '%"+ parametros.getDescripcionActividad()+"%'";
        }
        sql.append(
                "SELECT " +
                 " ACT.ACTIVIDAD_PK,PA.PLANANULA_PK,PA.CTITULO," +
                 " PA.CPERIODO,ACT.CDESCRIPCION,ACT.FCREACION,ACT.NESTADO, " +
                 " CASE ACT.NESTADO WHEN 1 THEN 'ACTIVA' ELSE 'INACTIVO' END descripcionestado "+
                " FROM "+ Constante.ESQUEMA +".tcplananual PA "+
                " INNER JOIN "+ Constante.ESQUEMA +".tvactividad ACT ON PA.PLANANULA_PK=ACT.PLANANULA_ID"+
                " WHERE 1=1 "+  filtro);
        lista = namedParameterJdbcTemplate.query(sql.toString(),params, BeanPropertyRowMapper.newInstance(Actividad.class));
        return lista;
    }

    @Override
    public void insertarPlanActividad(RegistroPlanAnualActividad registro) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO "+Constante.ESQUEMA+".tvactividad "+
            "(PLANANULA_ID,CDESCRIPCION,FAPERTURA,FCREACION,NESTADO) "+
            "VALUES(:P_PLANANULA_ID,:P_DESCRIPCION,:P_FAPERTURA,:P_FCREACION,:P_NESTADO)");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("P_PLANANULA_ID", registro.getCodigoPlanAnual());
        params.addValue("P_DESCRIPCION", registro.getNombreActividad());
        params.addValue("P_FAPERTURA", Fechas.getDate());
        params.addValue("P_FCREACION", Fechas.getDate());
        params.addValue("P_NESTADO",Constante.ESTADO_ACTIVO);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql.toString(), params, keyHolder, new String[]{"ACTIVIDAD_PK"});
        log.info("---------"+keyHolder.getKey().intValue());

    }
}
