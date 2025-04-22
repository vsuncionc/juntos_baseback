package base.juntos.base_back.service.impl;

import base.juntos.base_back.dto.request.*;
import base.juntos.base_back.dto.response.GeneracionCierrePadronResponse;
import base.juntos.base_back.model.*;
import base.juntos.base_back.repository.PadronRepository;
import base.juntos.base_back.service.PadronService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class PadronServiceImpl implements PadronService {
    private final PadronRepository padronRepository;

    @Override
    public Map<Integer, String> ProcesarPadron(PadronProcesarRequest parametros) {
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
        return padronRepository.ProcesarPadron(parametros);
    }

    @Override
    public List<HogaresPadronRevaluacion> listaHogaresPadron(PadronBuscarHogaresRequest parametros) {
        return padronRepository.listaHogaresPadron(parametros);
    }

    @Override
    public List<ResumenGeneracionPadron> resumenPadronGenerado(PadronBuscarRequest parametros) {
        return padronRepository.resumenPadronGenerado(parametros);
    }


    @Override
    public List<HogaresPadronRevaluacion> listaHogaresSeleccionadosPorPadron(PadronSeleccionHogaresRequest parametros) {
        String codigosPadronDet = "";
        int i =0;
        //PASAMOS LA LISTA A UN STRING
        for(Long idPadronDet: parametros.getIdetpadrones()) {
            i++;
            if (i == 1) {
                codigosPadronDet = idPadronDet.toString();
            }else{
                codigosPadronDet = codigosPadronDet.concat(",").concat(idPadronDet.toString());
            }

        }

        //parametros.setMonto(cadenaToDecimal(parametros.getMontopagar()));
        parametros.setCodigoDetPadron(codigosPadronDet);
        return padronRepository.listaHogaresSeleccionadosPorPadron(parametros);
    }

    @Override
    public Map<Integer,String> ProcesarPreCierre(PadronSeleccionHogaresRequest parametros) {
        String codigosPadronDet = "";
        int i =0;
        //PASAMOS LA LISTA A UN STRING
        for(Long idetPadronDet: parametros.getIdetpadrones()) {
            i++;
            if (i == 1) {
                codigosPadronDet = idetPadronDet.toString();
            }else{
                codigosPadronDet = codigosPadronDet.concat(",").concat(idetPadronDet.toString());
            }
        }
        parametros.setCodigoDetPadron(codigosPadronDet);
        return padronRepository.ProcesarPreCierre(parametros);
    }

    @Override
    public List<HogaresPreValidadosAptos> lsHogaresAptosPreCierre(PadronPreCierreRequest parametros) {
        return padronRepository.lsHogaresAptosPreCierre(parametros);
    }

    @Override
    public List<HogaresPreValidadoSuspendido> lsHogaresSuspendidosPreCierre(PadronPreCierreRequest parametros) {
        return padronRepository.lsHogaresSuspendidosPreCierre(parametros);
    }

    @Override
    public List<GeneracionCierrePadronResponse>  ProcesarCierre(PadronCierreRequest parametros) {
        return padronRepository.ProcesarCierre(parametros);
    }

    @Override
    public List<InformacionCabeceraPreCierre> informacionCabeceraPreCierre(PadronPreCierreRequest parametros) {
        return padronRepository.informacionCabeceraPreCierre(parametros);
    }

    @Override
    public List<HogaresValidadosAptos> lsHogaresAptosCierre(PadronPreCierreRequest parametros) {
        return padronRepository.lsHogaresAptosCierre(parametros);
    }

    @Override
    public List<HogaresValidadoSuspendido> lsHogaresSuspendidosCierre(PadronPreCierreRequest parametros) {
        return padronRepository.lsHogaresSuspendidosCierre(parametros);
    }

    @Override
    public List<HogaresValidadosAptosExcel> lsHogaresExcelAptosCierre(PadronPreCierreRequest parametros) {
        return padronRepository.lsHogaresExcelAptosCierre(parametros);
    }

    @Override
    public List<HogaresValPreCierreTodos> lsHogaresValPreCierreExcel(PadronPreCierreRequest parametros) {
        return padronRepository.lsHogaresValPreCierreExcel(parametros);
    }
}
