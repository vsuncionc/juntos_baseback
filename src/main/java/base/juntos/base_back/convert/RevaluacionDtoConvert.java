package base.juntos.base_back.convert;

import base.juntos.base_back.dto.response.RevaluacionResponse;
import base.juntos.base_back.model.BusquedaRevaluacion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RevaluacionDtoConvert {

 public RevaluacionResponse convertRevaluacionDto(BusquedaRevaluacion revaluacion){
   return new   RevaluacionResponse(
                 revaluacion.getId(),
                 revaluacion.getExpediente(),
                 revaluacion.getDocumento(),
                 revaluacion.getFechaDocumento(),
                 revaluacion.getFechaProceso(),
                 revaluacion.getDescripcion(),
                 revaluacion.getEstado(),
                 revaluacion.getCantidadHogares(),
                 revaluacion.getAbonoGnerado(),
                 revaluacion.getTipoPago(),
                 revaluacion.getTipoEsquema()
   );
 };

 public List<RevaluacionResponse> convertRevaluacionDto(List<BusquedaRevaluacion> revaluacion){
   return   revaluacion.stream()
           .map(this::convertRevaluacionDto)
           .toList();
 }



}
