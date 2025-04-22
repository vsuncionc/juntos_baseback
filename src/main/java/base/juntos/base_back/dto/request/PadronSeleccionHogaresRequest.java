package base.juntos.base_back.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PadronSeleccionHogaresRequest {
    List<Long> idetpadrones;
    String codigoDetPadron;
    String codigoUsuario;
}
