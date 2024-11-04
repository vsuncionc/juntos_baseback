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
public class Usuario {

    @Id
    @Column(name = "COD_USUARIO")
    private String codUsuario;
    @Column(name = "X_NOMBRES")
    private String nombre;
    @Column(name = "X_APMATERNO")
    private String apellidoPaterno;
    @Column(name = "X_APPATERNO")
    private String apellidoMaterno;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "C_DEPA")
    private String departamento_1;
    @Column(name = "C_DEPA2")
    private String departamento_2;
    @Column(name = "DES_PASSWORD")
    private String clave;



}
