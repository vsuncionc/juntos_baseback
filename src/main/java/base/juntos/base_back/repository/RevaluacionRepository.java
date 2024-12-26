package base.juntos.base_back.repository;


import base.juntos.base_back.dto.request.PadronRequest;
import base.juntos.base_back.dto.request.RevaluacionRequest;
import base.juntos.base_back.model.ResultadoRevaluacion;
import base.juntos.base_back.model.RevaluacionMiembrObjetivo;
import base.juntos.base_back.model.RevaluacionMimebroHogar;
import base.juntos.base_back.model.RevaluacionPadron;

import java.util.List;

public interface RevaluacionRepository {
    List<ResultadoRevaluacion> buscarRevaluaciones(RevaluacionRequest parametros);
    List<RevaluacionMimebroHogar> buscarMhRevaluacion(RevaluacionRequest parametros);
    List<RevaluacionMiembrObjetivo> buscarMoRevaluacion(RevaluacionRequest parametros);
    List<RevaluacionPadron> buscarRevaluacionesPadron(PadronRequest parametros);

}
