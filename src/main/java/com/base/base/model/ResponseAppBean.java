package com.base.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAppBean<T> {
    private String status;  // 1:success 0:error
    private String code;    // caso de error: codigo de error
    private String message; // caso de error: mensaje de error usuario
    private String messageBackend; // caso de error: mensaje de error usuario
    private T data;         // caso de exito: datos

}
