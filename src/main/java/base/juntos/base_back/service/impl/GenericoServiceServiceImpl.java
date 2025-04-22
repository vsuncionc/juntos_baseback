package base.juntos.base_back.service.impl;

import base.juntos.base_back.model.ComboGenericoNum;
import base.juntos.base_back.model.CombosGenerico;
import base.juntos.base_back.repository.GenericoRepository;
import base.juntos.base_back.service.GenericoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenericoServiceServiceImpl implements GenericoService {

    private final GenericoRepository genericoRepository;

    @Override
    public List<CombosGenerico> listaGrupoEsquema(String parametro) {
        return genericoRepository.listaGrupoEsquema(parametro);
    }

    @Override
    public List<ComboGenericoNum> listaPeriodo() {
        return genericoRepository.listaPeriodo();
    }
}
