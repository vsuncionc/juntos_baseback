package base.juntos.base_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ResumenGeneracionPadron {
    @Id
    @Column(name = "ITEM")
    private long id;
    @Column(name = "CODPADRONPAGO")
    private long codigopadron;
    @Column(name = "FECHA_PADRON")
    private String fechaPadron;
    @Column(name = "CANTIDAD")
    private int cantidadHogares;
    @Column(name = "TOTAL")
    private BigDecimal montoTotal;
    @Column(name = "USUARIO_REGISTRO")
    private String usuarioRegistro;
}
