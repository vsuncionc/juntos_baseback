package base.juntos.base_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HogaresPadronRevaluacion implements Serializable {
    @Id
    @Column(name = "SECPADRONPAGODETALLEPEND")
    private long id;
    @Column(name = "TIPOESQUEMA")
    private String tipoEsquema;
    @Column(name = "UT")
    private String region ;
    @Column(name = "X_DEPA")
    private String  departamento;
    @Column(name = "X_PROV")
    private String  provincia;
    @Column(name = "X_DIST")
    private String  distrito;
    @Column(name = "X_POBLADO")
    private String  centroPoblado;
    @Column(name = "IDCORTE")
    private int idCorte;
    @Column(name = "CODPERIODO")
    private String codigoPeriodo;
    @Column(name = "IDHOGAR")
    private long idHogar;
    @Column(name = "CODIGOHOGAR")
    private long codigoHogar;
    @Column(name = "CODPADRONPAGO")
    private long codigoPadron;
    @Column(name = "ESTADOCUENTA")
    private String estadoCuenta;
    @Column(name = "FECPADRON")
    private String fechaPadron;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "EXPEDIENTE")
    private String expediente;
    @Column(name = "DOCUMENTO")
    private String documento;
    @Column(name = "MONTO")
    private long monto;
    @Column(name = "NOMBRECOMPLETO")
    private String titular;
}
