package com.base.base.convert;

import com.base.base.dto.response.ActividadResponse;
import com.base.base.model.Actividad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActividadConvert {

    public ActividadResponse ConvertActividadToDto(Actividad actividad){
      return new ActividadResponse(
         actividad.getActividad_pk(),
         actividad.getCdescripcion(),
         actividad.getDescripcionEstado()
      );
    }

    public List<ActividadResponse> ConvertActividadToDto(List<Actividad> actividad){
       return actividad.stream()
               .map(this::ConvertActividadToDto)
               .toList();
    }


}
