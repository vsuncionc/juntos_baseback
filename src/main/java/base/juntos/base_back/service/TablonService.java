package base.juntos.base_back.service;

import base.juntos.base_back.dto.request.TablonBuscarRequest;
import base.juntos.base_back.model.HogaresTablonExcel;
import base.juntos.base_back.model.TablonCierrePadron;

import java.util.List;

public interface TablonService {

    List<TablonCierrePadron> listaTablones(TablonBuscarRequest parametros);
    List<HogaresTablonExcel> listadoTablonExcel(Long id);
}
