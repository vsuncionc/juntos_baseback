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
public class RevaluacionMimebroHogar {

    @Id
    @Column(name = "ITEM")
    private long item ;
    @Column(name = "ID_CORTE")
    private long codigoCorte ;
    @Column(name = "COD_PERIODO")
    private String codigoPeriodo ;
    @Column(name = "TIPO_ESQUEMA")
    private String tipoEsquema;
    @Column(name = "CODIGOHOGAR")
    private long codigoHogar ;
    @Column(name = "IDHOGAR")
    private long idHogar ;
    @Column(name = "DNI_TITULAR")
    private String dniTitular ;
    @Column(name = "NOMBRE")
    private String nombre ;
    @Column(name = "MES_1")
    private String mes1 ;
    @Column(name = "MES_2")
    private String mes2 ;

}
