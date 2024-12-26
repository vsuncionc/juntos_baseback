package base.juntos.base_back.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevaluacionRequest {
    long codigoRevaluacion;
    String  expediente;
    String  documento;
    String  descripcion;
    Integer grupoesquema;
}
