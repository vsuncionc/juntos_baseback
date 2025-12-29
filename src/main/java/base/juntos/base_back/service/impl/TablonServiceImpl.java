package base.juntos.base_back.service.impl;

import base.juntos.base_back.dto.request.TablonBuscarRequest;
import base.juntos.base_back.model.HogaresTablonExcel;
import base.juntos.base_back.model.MiembrosObjetivosHogar;
import base.juntos.base_back.model.TablonCierrePadron;
import base.juntos.base_back.repository.TablonRepository;
import base.juntos.base_back.service.TablonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TablonServiceImpl implements TablonService {

    private final TablonRepository tablonRepository;

    @Override
    public List<TablonCierrePadron> listaTablones(TablonBuscarRequest parametros) {
        return tablonRepository.listaTablones(parametros);
    }

    @Override
    public List<HogaresTablonExcel> listadoTablonExcel(Long id) {
        return tablonRepository.reporteTablon(id);
    }

    @Override
    public List<MiembrosObjetivosHogar> listaMoHogares(Long id) {
        return tablonRepository.listaMoHogares(id);
    }
}
