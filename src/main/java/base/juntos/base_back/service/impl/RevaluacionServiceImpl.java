package base.juntos.base_back.service.impl;

import base.juntos.base_back.dto.request.PadronRequest;
import base.juntos.base_back.dto.request.RevaluacionRequest;
import base.juntos.base_back.model.ResultadoRevaluacion;
import base.juntos.base_back.model.RevaluacionMiembrObjetivo;
import base.juntos.base_back.model.RevaluacionMimebroHogar;
import base.juntos.base_back.model.RevaluacionPadron;
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
    public List<ResultadoRevaluacion> buscarRevaluaciones(RevaluacionRequest parametros) {
        return revaluacionRepository.buscarRevaluaciones(parametros);
    }

    @Override
    public List<RevaluacionMimebroHogar> buscarMhRevaluacion(RevaluacionRequest parametros) {
        return revaluacionRepository.buscarMhRevaluacion(parametros);
    }

    @Override
    public List<RevaluacionMiembrObjetivo> buscarMoRevaluacion(RevaluacionRequest parametros) {
        return revaluacionRepository.buscarMoRevaluacion(parametros);
    }

    @Override
    public List<RevaluacionPadron> buscarRevaluacionesPadron(PadronRequest parametros) {
        return revaluacionRepository.buscarRevaluacionesPadron(parametros);
    }
}
