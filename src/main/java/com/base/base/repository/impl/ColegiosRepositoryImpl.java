package com.base.base.repository.impl;

import com.base.base.dto.request.ActActivodadColegioRequest;
import com.base.base.dto.request.ConsultActividadRequest;
import com.base.base.dto.request.ConsultaColegiosRequest;
import com.base.base.dto.request.RegistrarColegioPlanRequest;
import com.base.base.model.*;
import com.base.base.repository.ActividadRepository;
import com.base.base.repository.ColegiosRepository;
import com.base.base.util.Constante;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class ColegiosRepositoryImpl implements ColegiosRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final ActividadRepository actividadRepository;
    @Override
    public List<Colegio> buscarColegios(ConsultaColegiosRequest consultaColegiosRequest) {
        StringBuilder sql = new StringBuilder();
        List<Colegio> lista;
        String filtro= "";

        MapSqlParameterSource params = new MapSqlParameterSource();
        if(consultaColegiosRequest.getNombre()!=null && !consultaColegiosRequest.getNombre().isEmpty()){
            filtro ="AND UPPER(CNOMBRE) LIKE LIKE '%"+ consultaColegiosRequest.getNombre()+"%'";
        }

        if(consultaColegiosRequest.getDepa()!=null && !consultaColegiosRequest.getDepa().isEmpty()){
            filtro="AND CDEPA= :P_CDEPA";
            params.addValue("P_CDEPA", consultaColegiosRequest.getDepa());
        }

        if(consultaColegiosRequest.getProv()!=null && !consultaColegiosRequest.getProv().isEmpty()){
            filtro="AND CPROV= :P_CPROV";
            params.addValue("P_CPROV", consultaColegiosRequest.getProv());
        }

        if(consultaColegiosRequest.getDist()!=null && !consultaColegiosRequest.getDist().isEmpty()){
            filtro="AND CDIST= :P_CDIST";
            params.addValue("P_CDIST", consultaColegiosRequest.getDist());
        }

        if(consultaColegiosRequest.getPoblado()!=null && !consultaColegiosRequest.getPoblado().isEmpty()){
            filtro="AND CPOBLADO= :P_CPOBLADO";
            params.addValue("P_CPOBLADO", consultaColegiosRequest.getPoblado());
        }
        sql.append(
           "SELECT " +
              "IIEE_PK,CNOMBRE,CDESCRIPCION,CDEPA,CPROV,CDIST,\n" +
              "CPOBLADO,CDIRECCION,FCREACION,FACTUALIZA,NESTADO\n" +
            " FROM "+Constante.ESQUEMA +".TCIIEE WHERE 1=1 "+  filtro);
        lista = namedParameterJdbcTemplate.query(sql.toString(),params, BeanPropertyRowMapper.newInstance(Colegio.class));
        return lista;
    }

    @Override
    public int insertarPlanColegio(RegistrarColegioPlanRequest request) {
        int respuesta = 0;
        int codigoColegioPlan =0;

         //BUSCAMOS LAS ACTIVIDADES POR PLAN
          ConsultActividadRequest actividadRequest = new ConsultActividadRequest();
          actividadRequest.setCodigoPlanAnual(request.getCodigoPlanAnual());
          List<Actividad> listaActividad = actividadRepository.busquedaActividad(actividadRequest);

          if(listaActividad.size()>0){
            for(ColegioPlan item : request.getListaColegios()){
                StringBuilder sql = new StringBuilder();
                //INSERTAMOS LA CABECEFRA EL PLAN Y LOS COLEGIO
                if(item.getCodigoColegio()>0 && request.getCodigoPlanAnual()>0){
                    sql.append("INSERT INTO "+Constante.ESQUEMA+".tvplananualiiee"+
                     "(IIEE_PK,PLANANULA_ID,NESTADO,FCREACION) VALUES(:P_IIEE,:P_PLAN,:P_ESTADO,CURRENT_TIMESTAMP)");
                    MapSqlParameterSource params = new MapSqlParameterSource();
                    params.addValue("P_IIEE",item.getCodigoColegio());
                    params.addValue("P_PLAN",request.getCodigoPlanAnual());
                    params.addValue("P_ESTADO",Constante.ESTADO_ACTIVO);
                    KeyHolder keyHolder = new GeneratedKeyHolder();
                    namedParameterJdbcTemplate.update(sql.toString(), params, keyHolder, new String[]{"PLANANUALIIEE_PK"});
                    codigoColegioPlan = keyHolder.getKey().intValue();

                    //INSERTAMOS LAS ACTIVIDADES
                      for(Actividad itemAct : listaActividad){
                          StringBuilder sqlIIEEactividad = new StringBuilder();
                          sqlIIEEactividad.append("INSERT INTO "+Constante.ESQUEMA+".tmdplananualiiee"+
                            "(PLANANUALIIEE_ID,ACTIVIDAD_ID,NESTADO,FCREACION) VALUES(:P_PLANANUALIIEE_ID,:P_ACTIVIDAD_ID,:P_ESTADO,CURRENT_TIMESTAMP)");
                          MapSqlParameterSource params2 = new MapSqlParameterSource();
                          params2.addValue("P_PLANANUALIIEE_ID",codigoColegioPlan);
                          params2.addValue("P_ACTIVIDAD_ID",itemAct.getActividad_pk());
                          params2.addValue("P_ESTADO",Constante.ESTADO_ACTIVO);
                          namedParameterJdbcTemplate.update(sqlIIEEactividad.toString(), params2);
                      }

                    respuesta = 1;
                }else{
                    respuesta = 0;
                }
            }
         }

         return respuesta;
    }

    @Override
    public void actualizarActividad(ActActivodadColegioRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE "+Constante.ESQUEMA+".tmdplananualiiee SET "+
         "CNOMBRE= :P_NOMBRE,CFORMATO=:P_FORMATO,FACTUALIZA=CURRENT_TIMESTAMP,NESTADO=:P_ESTADO," +
         "CPERIODO= :P_PERIODO WHERE DPLANANUALIIEE_PK=:P_PLANANUALIIEE");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("P_NOMBRE",request.getNombreArchivo());
        params.addValue("P_FORMATO",request.getFormato());
        params.addValue("P_ESTADO",Constante.ESTADO_ACTIVO);
        params.addValue("P_PERIODO",request.getPeriodo());
        params.addValue("P_PLANANUALIIEE",request.getCodigodt());
        namedParameterJdbcTemplate.update(sql.toString(), params);
    }

    @Override
    public List<ActividadPlanColegio> listaActividadPorColegio(ActActivodadColegioRequest request) {
        StringBuilder sql = new StringBuilder();
        String filtro= "";
        MapSqlParameterSource params = new MapSqlParameterSource();

        if(request.getCodigoColegio()>0){
           filtro=" AND PAIIEE.IIEE_PK=:P_IIEE";
           params.addValue("P_IIEE",+request.getCodigoColegio());
        }
        sql.append("SELECT "+
           "DTACT.DPLANANUALIIEE_PK CODIGO,PLAN.CTITULO, ACT.CDESCRIPCION,DTACT.DPLANANUALIIEE_PK,DTACT.CNOMBRE,DTACT.CFORMATO,"+
           "CASE DTACT.NESTADO WHEN 1 THEN 'ABIERTO' ELSE 'CERRADO' END ESTADO,DTACT.FACTUALIZA,"+
           "DTACT.PLANANUALIIEE_ID,DTACT.ACTIVIDAD_ID,DTACT.FCREACION,DTACT.CUSUCREA,DTACT.CUSUACT,DTACT.CPERIODO "+
           " FROM "+Constante.ESQUEMA+".tmdplananualiiee DTACT "+
           " INNER JOIN "+Constante.ESQUEMA+".tvplananualiiee PAIIEE ON DTACT.PLANANUALIIEE_ID=PAIIEE.PLANANUALIIEE_PK AND DTACT.VIGENTE='1' AND PAIIEE.VIGENTE='1' "+
           " INNER JOIN "+Constante.ESQUEMA+".tcplananual PLAN ON PLAN.PLANANULA_PK=PAIIEE.PLANANULA_ID AND PLAN.VIGENTE='1' "+
           " INNER JOIN "+Constante.ESQUEMA+".tvactividad ACT ON ACT.ACTIVIDAD_PK=DTACT.ACTIVIDAD_ID AND ACT.VIGENTE='1' "+
           " WHERE 1=1 "+filtro+" ORDER BY ACT.PLANANULA_ID");

        return namedParameterJdbcTemplate.query(sql.toString(),params, BeanPropertyRowMapper.newInstance(ActividadPlanColegio.class));
    }

    @Override
    public List<PlanAnualnstitucion> listarPlanesPorColegio(int codigoColegio) {
        StringBuilder sql = new StringBuilder();
        String filtro= "";
        MapSqlParameterSource params = new MapSqlParameterSource();
        if(codigoColegio >0){
            filtro=" AND IIEE.IIEE_PK=:IIEE";
            params.addValue("IIEE",+codigoColegio);
        }

        sql.append("SELECT " +
          "PAIIEE.PLANANUALIIEE_PK,PLAN.CTITULO plan,IIEE.CNOMBRE colegio,PLAN.CPERIODO,PLAN.CANIO,PLAN.CMES,PLAN.FCREACION,"+
          "CASE PAIIEE.NESTADO WHEN 1 THEN 'ABIERTO' ELSE 'CERRADO' END ESTADO,IIEE.IIEE_PK,PLAN.PLANANULA_PK "+
          " FROM "+Constante.ESQUEMA+".tvplananualiiee PAIIEE "+
          " INNER JOIN "+Constante.ESQUEMA+".tcplananual PLAN ON PLAN.PLANANULA_PK=PAIIEE.PLANANULA_ID AND PLAN.VIGENTE='1' "+
          " INNER JOIN "+Constante.ESQUEMA+".tciiee IIEE ON IIEE.IIEE_PK=PAIIEE.IIEE_PK AND IIEE.VIGENTE='1' "+
          " WHERE 1=1 "+filtro+" ORDER BY PLAN.CPERIODO DESC");

        return namedParameterJdbcTemplate.query(sql.toString(),params, BeanPropertyRowMapper.newInstance(PlanAnualnstitucion.class));
    }
}
