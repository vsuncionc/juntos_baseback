package base.juntos.base_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TablonCierrePadron {
    @Id
    @Column(name = "ITEM")
    private Long id;
    @Column(name = "ID_PREVALHOGAR")
    private Long codigo;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "PFREGISTRO")
    private String fechaRegistro;
    @Column(name = "PFTERMINO")
    private String fechaTermino;
    @Column(name = "CANT_APTOS")
    private int cantidadAptos;
    @Column(name = "CANT_SUSPENDIDOS")
    private int cantidadSuspendidos;
    @Column(name = "CANT_ERROR")
    private int cantidadError;
    @Column(name = "CANT_TOTAL")
    private int cantidadTotal;
    @Column(name = "MONTO")
    private BigInteger monto;
}
