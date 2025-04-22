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
public class HogaresExcelGenPadron {

    @Id
    @Column(name = "CODIGO")
    private long id;
    @Column(name = "X_EXPEDIENTE")
    private String expediente ;
    @Column(name = "X_DOCUMENTO")
    private String documento ;
    @Column(name = "IDCORTE")
    private long idcorte ;
    @Column(name = "CODPERIODO")
    private String periodo ;
    @Column(name = "IDHOGAR")
    private long idhogar ;
    @Column(name = "CODIGOHOGAR")
    private long codigoHogar ;
    @Column(name = "MES1")
    private String mes1 ;
    @Column(name = "MES2")
    private String mes2 ;
    @Column(name = "MONTO")
    private BigInteger monto ;
    @Column(name = "ESQUEMA")
    private String esquema ;

}
