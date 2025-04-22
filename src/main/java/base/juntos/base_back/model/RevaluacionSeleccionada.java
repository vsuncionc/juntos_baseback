package base.juntos.base_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RevaluacionSeleccionada implements Serializable {
    @Id
    @Column(name = "IDTREEVALUACIONES_DET")
    private Long codigo;
    @Column(name = "TIPO_ESQUEMA")
    private String tipoEsquema;
    @Column(name = "X_DOCUMENTO")
    private String documento;
    @Column(name = "X_EXPEDIENTE")
    private String expediente;
    @Column(name = "CODIGOHOGAR")
    private Long codigoHogar;
    @Column(name = "IDHOGAR")
    private Long idHogar;
    @Column(name = "COD_PERIODO")
    private String periodo;
    @Column(name = "MES_1")
    private String mes1;
    @Column(name = "MES_2")
    private String mes2;
    @Column(name = "NOM_TITULAR")
    private String nombreTitular;
    @Column(name = "IDREEVALUACION")
    private Long codigoRevaluacion;
    @Column(name = "ID_CORTE")
    private Long idcorte;
    @Column(name = "MONTO")
    private Long monto;


}
