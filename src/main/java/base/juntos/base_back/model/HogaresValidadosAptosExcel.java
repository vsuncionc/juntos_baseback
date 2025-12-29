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
public class HogaresValidadosAptosExcel {

    @Id
    @Column(name = "ITEM")
    private long id;
    @Column(name = "X_REGION")
    private String region;
   @Column(name = "X_DEPA")
    private String departamento;
    @Column(name = "X_PROV")
    private String provincia;
    @Column(name = " X_DIST")
    private String distrito;
    @Column(name = "X_POBLADO")
    private String centroPoblado;
    @Column(name = "IDHOGAR")
    private long idhogar;
    @Column(name = "CODIGOHOGAR")
    private long codigoHogar;
    @Column(name = "IDPERSONA")
    private long idPersona;
    @Column(name = "DNI")
    private String dni;
    @Column(name = "NOMBRE_TITULAR")
    private String nombreTitular;
    @Column(name = "APEPATERNO")
    private String apellidoPaterno;
    @Column(name = "APEMATERNO")
    private String apellidoMaterno;
    @Column(name = "FECHA_FALLECIDO")
    private String fechaFallecido;
    @Column(name = "FALLECIDO")
    private String fallecido;
    @Column(name = "ALERTA")
    private String alerta;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "X_EXPEDIENTE")
    private String expediente;
    @Column(name = " X_DOCUMENTO")
    private String documento;
    @Column(name = "ID_CORTE")
    private long idcorte;
   @Column(name = "COD_PERIODO")
    private String codigoPeriodo;
    @Column(name = "CUMPLIMIENTO")
    private int cumplimiento;
   @Column(name = "CUMPLIMIENTO_EVALUADO")
    private String cumplimientoEvaluado;
     @Column(name = "MESES")
    private String meses;
    @Column(name = "CODESTADOHOGAR")
    private String estadoHogar;
    @Column(name = "CUENTA")
    private String cuenta;
    @Column(name = "ESTADOCUENTA")
    private String estadoCuenta;
    @Column(name = "IDREEVALUACION")
    private String idRevaluacion;
}
