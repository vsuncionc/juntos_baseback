package base.juntos.base_back.convert;


import base.juntos.base_back.dto.response.RevaluacionMhResponse;
import base.juntos.base_back.dto.response.RevaluacionMoResponse;
import base.juntos.base_back.dto.response.RevaluacionResponse;
import base.juntos.base_back.model.ResultadoRevaluacion;
import base.juntos.base_back.model.RevaluacionMiembrObjetivo;
import base.juntos.base_back.model.RevaluacionMimebroHogar;
import base.juntos.base_back.model.RevaluacionPadron;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RevaluacionDtoConvert {

 public RevaluacionResponse convertRevaluacionDto(ResultadoRevaluacion revaluacion){
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
 }

 public List<RevaluacionResponse> convertRevaluacionDto(List<ResultadoRevaluacion> revaluacion){
   return   revaluacion.stream()
           .map(this::convertRevaluacionDto)
           .toList();
 }

 public RevaluacionMhResponse convertRevaluacionMhDto(RevaluacionMimebroHogar revaluacion){
    return new RevaluacionMhResponse(
            revaluacion.getCodigoCorte(),
            revaluacion.getCodigoPeriodo(),
            revaluacion.getTipoEsquema(),
            revaluacion.getCodigoHogar(),
            revaluacion.getIdHogar(),
            revaluacion.getDniTitular(),
            revaluacion.getNombre(),
            revaluacion.getMes1(),
            revaluacion.getMes2()
    );
 }

    public List<RevaluacionMhResponse> convertRevaluacionMhDto(List<RevaluacionMimebroHogar> revaluacion){
        return revaluacion.stream()
                .map(this::convertRevaluacionMhDto)
                .toList();
    }


    public RevaluacionMoResponse convertRevaluacionMoDto(RevaluacionMiembrObjetivo revaluacion){
        return  new RevaluacionMoResponse(
                revaluacion.getItem(),
                revaluacion.getCodigoCorte(),
                revaluacion.getCodigoPeriodo(),
                revaluacion.getTipoEsquema(),
                revaluacion.getCodigoHogar(),
                revaluacion.getIdHogar(),
                revaluacion.getDniTitular(),
                revaluacion.getNombre(),
                revaluacion.getMes1(),
                revaluacion.getMes2()
        );
    }

    public List<RevaluacionMoResponse> convertRevaluacionMoDto(List<RevaluacionMiembrObjetivo> revaluacion){
        return revaluacion.stream()
                .map(this::convertRevaluacionMoDto)
                .toList();
    }

    public RevaluacionPadron convertRevaluacionPadronDto(RevaluacionPadron parametro){
     return new RevaluacionPadron(
             parametro.getItem(),
             parametro.getCodigoRevaluacion(),
             parametro.getTipoEsquema(),
             parametro.getExpediente(),
             parametro.getDocumento(),
             parametro.getFechaProceso(),
             parametro.getCodigoPadron(),
             parametro.getCantidadHogares()
     );
    }


    public List<RevaluacionPadron> convertRevaluacionPadronDto(List<RevaluacionPadron> parametro){
     return parametro.stream()
             .map(this::convertRevaluacionPadronDto)
             .toList();
    }

}
