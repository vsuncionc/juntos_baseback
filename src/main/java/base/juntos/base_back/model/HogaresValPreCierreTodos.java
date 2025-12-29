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
    @Column(name = "CODPADRONPAGO")
    private long codigoPadronPago;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "NUMRESOLUCION")
    private String numeroResolucion;
    @Column(name = "MOTIVO_PAGO")
    private String motivoPago;
    @Column(name = "DESTIPOPADRON")
    private String tipoPadron;
    @Column(name = "CODPERIODO")
    private String codigoPeriodo;
    @Column(name = "IDHOGAR")
    private long idhogar;
    @Column(name = "CODIGOHOGAR")
    private long codigoHogar;
    @Column(name = "MONTO_PAGO")
    private String monto;
    @Column(name = "CODMOTIVONOPAGO")
    private String codigoMotivoNoPago;
    @Column(name = "MOT_NOABONO")
    private String motivoNoAbono;
    @Column(name = "C_REGION")
    private String codigoRegion;
    @Column(name = "X_REGION")
    private String region;
    @Column(name = "UBIGEO")
    private String ubigeoHogar;
    @Column(name = "X_DEPA")
    private String departamento;
    @Column(name = "X_PROV")
    private String provicnia;
    @Column(name = "X_DIST")
    private String distrito;
    @Column(name = "ESTHOGAR_DESC")
    private String estadoHogar;
    @Column(name = "F_DESAFILIACION")
    private String fechaDesafiliacion;
    @Column(name = "IDPERSONA_TITACT")
    private long idpersonaTitular;
    @Column(name = "NOMBRE_COMPLETO_TITACT")
    private String nomnbreTitular;
    @Column(name = "DNI_TITACT")
    private String dniTitular;
    @Column(name = "CUENTA_TITACT")
    private String numeroCuentaTitular;
    @Column(name = "EST_CUENTA")
    private String estadoCuenta;
    @Column(name = "FECFALLECIMIENTO")
    private String fechaFallecimiento;
    @Column(name = "FALLECIDO")
    private String fallecido;
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "FECIERRE")
    private String fechaCierre;
    @Column(name = "ESTADO_VALIDACION")
    private String estadoValidacion;
    @Column(name = "OBS_REG")
    private String observacionValidacion;
}
