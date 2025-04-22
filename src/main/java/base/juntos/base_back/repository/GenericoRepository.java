package base.juntos.base_back.repository;

import base.juntos.base_back.model.ComboGenericoNum;
import base.juntos.base_back.model.CombosGenerico;

import java.util.List;
import java.util.Map;

public interface GenericoRepository {
    List<CombosGenerico> listaGrupoEsquema(String parametro);
    List<ComboGenericoNum>listaPeriodo();
}
