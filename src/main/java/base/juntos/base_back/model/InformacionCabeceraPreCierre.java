package base.juntos.base_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class InformacionCabeceraPreCierre {

    @Id
    @Column(name = "ID_PREVALHOGAR")
    private long id;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "TOTA_HOGARES")
    private String totalHogares;

    @Column(name = "CANT_HGAPTOS")
    private String CantidadAptos;

    @Column(name = "CANT_HGSUSPENDIDOS")
    private String CantidadSuspendidos;

    @Column(name = "CANT_ERROR")
    private String CantidadError;

    @Column(name = "MONTO_PAGAR",precision = 10, scale = 2)
    private BigDecimal MontoTotal;

    @Column(name = "FECPROCESOFINALIZA")
    private String fechaProcesamiento;


}
