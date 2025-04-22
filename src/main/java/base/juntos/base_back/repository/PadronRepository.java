package base.juntos.base_back.repository;

import base.juntos.base_back.dto.request.*;
import base.juntos.base_back.dto.response.GeneracionCierrePadronResponse;
import base.juntos.base_back.model.*;

import java.util.List;
import java.util.Map;

public interface PadronRepository {
    Map<Integer,String> ProcesarPadron(PadronProcesarRequest parametros);
    List<ResumenGeneracionPadron> resumenPadronGenerado(PadronBuscarRequest parametros);
    List<HogaresPadronRevaluacion> listaHogaresPadron(PadronBuscarHogaresRequest parametros);
    List<HogaresPadronRevaluacion> listaHogaresSeleccionadosPorPadron(PadronSeleccionHogaresRequest parametros);
    Map<Integer,String> ProcesarPreCierre(PadronSeleccionHogaresRequest parametros);
    List<HogaresPreValidadosAptos> lsHogaresAptosPreCierre(PadronPreCierreRequest parametros);
    List<HogaresPreValidadoSuspendido> lsHogaresSuspendidosPreCierre(PadronPreCierreRequest parametros);
    List<GeneracionCierrePadronResponse> ProcesarCierre(PadronCierreRequest parametros);
    List<InformacionCabeceraPreCierre> informacionCabeceraPreCierre(PadronPreCierreRequest parametros);
    List<HogaresValidadosAptos> lsHogaresAptosCierre(PadronPreCierreRequest parametros);
    List<HogaresValidadoSuspendido> lsHogaresSuspendidosCierre(PadronPreCierreRequest parametros);
    List<HogaresValidadosAptosExcel> lsHogaresExcelAptosCierre(PadronPreCierreRequest parametros);
    List<HogaresValPreCierreTodos> lsHogaresValPreCierreExcel(PadronPreCierreRequest parametros);
}
