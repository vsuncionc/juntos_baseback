package base.juntos.base_back.service.Impl;

import base.juntos.base_back.dto.request.RevaluacionRequest;
import base.juntos.base_back.model.BusquedaRevaluacion;
import base.juntos.base_back.repository.RevaluacionRepository;
import base.juntos.base_back.service.RevaluacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RevaluacionServiceImpl implements RevaluacionService , Serializable {

     private final RevaluacionRepository revaluacionRepository;

    @Override
    public List<BusquedaRevaluacion> buscarRevaluaciones(RevaluacionRequest parametros) {
        return revaluacionRepository.buscarRevaluaciones(parametros);
    }
}
