package base.juntos.base_back.service;

import base.juntos.base_back.dto.request.PadronBuscarRequest;
import base.juntos.base_back.dto.request.PadronProcesarMoRequest;
import base.juntos.base_back.dto.request.PadronProcesarRequest;
import base.juntos.base_back.dto.request.RevaluacionRequest;
import base.juntos.base_back.model.*;

import java.util.List;
import java.util.Optional;

public interface RevaluacionService {
   RevaluacionInformacion infoRevaluacion(RevaluacionRequest parametros);
   List<ResultadoRevaluacion> buscarRevaluaciones(RevaluacionRequest parametros);
   List<RevaluacionMimebroHogar> buscarMhRevaluacion(RevaluacionRequest parametros);
   List<RevaluacionMiembrObjetivo> buscarMoRevaluacion(RevaluacionRequest parametros);
   List<RevaluacionPadron> buscarRevaluacionesPadron(PadronBuscarRequest parametros);
   List<RevaluacionSeleccionada> listarRevaluacionSeleccionda(PadronProcesarRequest parametros);
   List<RevaluacionMiembrObjetivoSelec>listarMoRevaluacionSeleccionda(PadronProcesarMoRequest parametros);
   List<HogaresExcelGenPadron> listaHogaresGneradosPadron(PadronBuscarRequest parametros);

}
