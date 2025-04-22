package base.juntos.base_back.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespuestaProcesamientoPadron implements Serializable {
    private long codigoPadron;
    private String fechaProcesamiento;
    private int cantidadHogares;
}
