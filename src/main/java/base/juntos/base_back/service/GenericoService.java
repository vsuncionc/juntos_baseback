package base.juntos.base_back.service;

import base.juntos.base_back.model.ComboGenericoNum;
import base.juntos.base_back.model.CombosGenerico;

import java.util.List;

public interface GenericoService {

    List<CombosGenerico>listaGrupoEsquema(String parametro);
    List<ComboGenericoNum>listaPeriodo();
}
