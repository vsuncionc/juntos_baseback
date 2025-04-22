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
public class ResultadoRevaluacion {
    @Id
    @Column(name = "IDREEVALUACION")
    private long id;
    @Column(name = "X_EXPEDIENTE")
    private String expediente;
    @Column(name = "X_DOCUMENTO")
    private String documento;
    @Column(name = "FECHA_DOC")
    private String fechaDocumento;
    @Column(name = "FECHA_PROC")
    private String fechaProceso;
    @Column(name = "X_DESCRIPCION")
    private String descripcion;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "N_HOGARES")
    private int cantidadHogares;
    @Column(name = "ABONO_GENERADO")
    private String tipoPago;
    @Column(name = "DESESQUEMA")
    private String tipoEsquema;

}
