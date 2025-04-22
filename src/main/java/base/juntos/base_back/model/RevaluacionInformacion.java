package base.juntos.base_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RevaluacionInformacion {

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
    private String fechaProduccion;
    @Column(name = "X_DESCRIPCION")
    private String descripcion;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "N_HOGARES")
    private int numeroHogar;
    @Column(name = "N_HACEPTADO")
    private int numeroAceptado;
    @Column(name = "N_DENEGADO")
    private int numeroDenegado;
    @Column(name = "GRUPO_ESQUEMA")
    private String grupoEsquema;

}
