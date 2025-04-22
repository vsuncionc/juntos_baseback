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
public class ComboGenericoNum {
    @Id
    @Column(name = "VALOR")
    private Long id;
    @Column(name = "DESCRIPCION")
    private String descripcion;
}
