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
public class MiembrosObjetivosHogar {

    @Id
    @Column(name = "NRO")
    private long id;
    @Column(name = "CODIGOHOGAR")
    private long codigoHogar;
    @Column(name = "IDHOGAR")
    private long idHogar;
    @Column(name = "X_REGION")
    private String region;
    @Column(name = "X_DEPA")
    private String departamento;
    @Column(name = "X_PROV")
    private String provincia;
    @Column(name = "X_DIST")
    private String distrito;
    @Column(name = "X_POBLADO")
    private String centroPoblado;
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreMo;
    @Column(name = "CUMPLE_VCC")
    private String cumplioVcc;
    @Column(name = "ID_CORTE_HOGAR_USUARIO")
    private long idCorteHogarUsuario;
    @Column(name = "IDPERSONA")
    private long idpersona;
    @Column(name = "DNI")
    private String dni;
}
