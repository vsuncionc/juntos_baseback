package base.juntos.base_back.service;

import base.juntos.base_back.dto.request.PadronBuscarRequest;
import base.juntos.base_back.dto.request.PadronPreCierreRequest;
import org.springframework.core.io.ByteArrayResource;
import java.io.IOException;

public interface GenerarExcelService {


    ByteArrayResource listarHogaresGenPadron(PadronBuscarRequest parametros)throws IOException;
    ByteArrayResource listaHogaresAptos(PadronPreCierreRequest parametros) throws IOException;
    ByteArrayResource listaHogaresPreValidadosTodos(PadronPreCierreRequest parametros) throws IOException;
    ByteArrayResource reporteTablon(Long id) throws IOException;
}
