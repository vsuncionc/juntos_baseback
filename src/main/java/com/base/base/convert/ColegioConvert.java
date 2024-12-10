package com.base.base.convert;

import com.base.base.dto.response.ActividadColegioConsulta;
import com.base.base.dto.response.ConsultaColegioResponse;
import com.base.base.dto.response.ListarPlanColegioResponse;
import com.base.base.model.ActividadPlanColegio;
import com.base.base.model.Colegio;
import com.base.base.model.PlanAnualnstitucion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColegioConvert {
    public ConsultaColegioResponse convertConsultaColegioToDto(Colegio colegio){
        return  new ConsultaColegioResponse(
                colegio.getIiee_pk(),
                colegio.getCnombre(),
                colegio.getCdescripcion(),
                colegio.getCdepa(),
                colegio.getCprov(),
                colegio.getCdist(),
                colegio.getCpoblado(),
                colegio.getCdireccion()
        ) ;
    }


    public List<ConsultaColegioResponse> convertConsultaPlanAnualToDto(List<Colegio> colegio){
        return colegio.stream()
                .map(this::convertConsultaColegioToDto)
                .toList();
    }


   public ActividadColegioConsulta convertActividadColegioDto(ActividadPlanColegio parametros){
     return  new ActividadColegioConsulta(
             parametros.getCODIGO(),
             parametros.getCTITULO(),
             parametros.getCDESCRIPCION(),
             parametros.getCNOMBRE(),
             parametros.getESTADO(),
             parametros.getFCREACION(),
             parametros.getFACTUALIZA()
     );
   }

    public List <ActividadColegioConsulta> convertActividadColegioDto(List<ActividadPlanColegio> parametros){
        return parametros.stream()
                .map(this::convertActividadColegioDto)
                .toList();
    }

    public ListarPlanColegioResponse convertListarPlanColegioDto(PlanAnualnstitucion parametros){
        return  new ListarPlanColegioResponse(
                parametros.getPlananualiiee_pk(),
                parametros.getPlananula_pk(),
                parametros.getIiee_pk(),
                parametros.getColegio(),
                parametros.getPlan(),
                parametros.getEstado(),
                parametros.getCperiodo(),
                parametros.getCanio(),
                parametros.getCmes(),
                parametros.getFcreacion()
        );
    }

    public List<ListarPlanColegioResponse> convertListarPlanColegioDto(List<PlanAnualnstitucion> parametros){
     return parametros.stream().
             map(this::convertListarPlanColegioDto).
             toList();
    }



}
