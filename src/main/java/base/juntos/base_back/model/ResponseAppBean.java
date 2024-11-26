package base.juntos.base_back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAppBean<T> {
    private String status;  // 1:success 0:error
    private String code;    // caso de error: codigo de error
    private String message; // caso de error: mensaje de error
    private T data;         // caso de exito: datos
    public String token;
}
