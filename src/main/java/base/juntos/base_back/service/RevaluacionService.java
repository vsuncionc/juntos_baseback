package base.juntos.base_back.service;

import base.juntos.base_back.dto.request.RevaluacionRequest;
import base.juntos.base_back.model.BusquedaRevaluacion;

import java.util.List;

public interface RevaluacionService {
   List<BusquedaRevaluacion> buscarRevaluaciones(RevaluacionRequest parametros);
}
