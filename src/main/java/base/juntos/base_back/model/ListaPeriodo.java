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
public class ListaPeriodo {

    @Id
    @Column(name = "ID_CORTE")
    private Long id;
    @Column(name = "COD_PERIODO")
    private String periodo;
}
