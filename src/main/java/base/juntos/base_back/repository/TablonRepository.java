package base.juntos.base_back.repository;

import base.juntos.base_back.dto.request.TablonBuscarRequest;
import base.juntos.base_back.model.HogaresTablonExcel;
import base.juntos.base_back.model.MiembrosObjetivosHogar;
import base.juntos.base_back.model.TablonCierrePadron;

import java.util.List;

public interface TablonRepository {
    List<TablonCierrePadron> listaTablones(TablonBuscarRequest parametros);
    List<HogaresTablonExcel> reporteTablon(Long id);
    List<MiembrosObjetivosHogar> listaMoHogares(Long id);
}
