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
public class HogaresValPreCierreTodos {

    @Id
    @Column(name = "ID_PREVALHOGARDET")
    private long id;
    @Column(name = "X_REGION")
    private String  region;
    @Column(name = "X_DEPA")
    private String  departamento;
    @Column(name = "X_PROV")
    private String  provincia;
    @Column(name = "X_DIST")
    private String  distrito;
    @Column(name = "X_POBLADO")
    private String  centroPoblado;
    @Column(name = "CODPADRONPAGO")
    private String  codigoPadron;
    @Column(name = "OBSERVACION")
    private String  observacion;
    @Column(name = "COD_PERIODO")
    private String  periodo;
    @Column(name = "IDHOGAR")
    private long  idhogar;
    @Column(name = "MONTO")
    private String monto ;
    @Column(name = "TITULAR")
    private String  titular;
    @Column(name = "ESQUEMA")
    private String  esquema;
    @Column(name = "VALIDACION")
    private String  validacion;

}
