package base.juntos.base_back.repository;


import base.juntos.base_back.dto.request.RevaluacionRequest;
import base.juntos.base_back.model.BusquedaRevaluacion;

import java.util.List;

public interface RevaluacionRepository {
    List<BusquedaRevaluacion> buscarRevaluaciones(RevaluacionRequest parametros);
}
