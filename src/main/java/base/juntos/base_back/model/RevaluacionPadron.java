package base.juntos.base_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RevaluacionPadron {

    @Id
    @Column(name = "ITEM")
    private long item;
    @Column(name = "IDREEVALUACION")
    private long codigoRevaluacion;
    @Column(name = "TIPO")
    private String tipoEsquema;
    @Column(name = "X_EXPEDIENTE")
    private String expediente;
    @Column(name = "X_DOCUMENTO")
    private String documento;
    @Column(name = "FECHA_PROC")
    private String fechaProceso;
    @Column(name = "CODPADRONPAGO")
    private String codigoPadron;
    @Column(name = "CANT_HOGAR")
    private int cantidadHogares;
}
