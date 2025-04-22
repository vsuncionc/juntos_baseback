package base.juntos.base_back.service.impl;

import base.juntos.base_back.dto.request.PadronBuscarRequest;
import base.juntos.base_back.dto.request.PadronProcesarMoRequest;
import base.juntos.base_back.dto.request.PadronProcesarRequest;
import base.juntos.base_back.dto.request.RevaluacionRequest;
import base.juntos.base_back.model.*;
import base.juntos.base_back.repository.RevaluacionRepository;
import base.juntos.base_back.service.RevaluacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RevaluacionServiceImpl implements RevaluacionService , Serializable {

     private final RevaluacionRepository revaluacionRepository;

    @Override
    public RevaluacionInformacion infoRevaluacion(RevaluacionRequest parametros) {
        return revaluacionRepository.infoRevaluacion(parametros);
    }

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
    public List<RevaluacionPadron> buscarRevaluacionesPadron(PadronBuscarRequest parametros) {
        return revaluacionRepository.buscarRevaluacionesPadron(parametros);
    }

    @Override
    public List<RevaluacionSeleccionada> listarRevaluacionSeleccionda(PadronProcesarRequest parametros) {
        String codigosRevaluacion = "";
        int i =0;
        //PASAMOS LA LISTA A UN STRING
        for(Long idrevaluacion: parametros.getIdrevaluaciones()) {
            i++;
            if (i == 1) {
                codigosRevaluacion = idrevaluacion.toString();
            }else{
                codigosRevaluacion = codigosRevaluacion.concat(",").concat(idrevaluacion.toString());
            }

        }

        //parametros.setMonto(cadenaToDecimal(parametros.getMontopagar()));
        parametros.setCodigoRevaluacion(codigosRevaluacion);
        return revaluacionRepository.listarRevaluacionSeleccionda(parametros);
    }

    @Override
    public List<RevaluacionMiembrObjetivoSelec> listarMoRevaluacionSeleccionda(PadronProcesarMoRequest parametros) {
        String codigosRevaluacion = "";
        int i =0;
        //PASAMOS LA LISTA A UN STRING
        for(Long idrevaluacion: parametros.getIdrevaluaciones()) {
            i++;
            if (i == 1) {
                codigosRevaluacion = idrevaluacion.toString();
            }else{
                codigosRevaluacion = codigosRevaluacion.concat(",").concat(idrevaluacion.toString());
            }

        }
        parametros.setCodigoRevaluacion(codigosRevaluacion);
        return revaluacionRepository.listarMoRevaluacionSeleccionda(parametros);
    }

    @Override
    public List<HogaresExcelGenPadron> listaHogaresGneradosPadron(PadronBuscarRequest parametros) {
        return revaluacionRepository.listaHogaresGneradosPadron(parametros);
    }
}
