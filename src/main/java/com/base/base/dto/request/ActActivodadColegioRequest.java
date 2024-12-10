package com.base.base.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActActivodadColegioRequest {
    private int codigodt;
    private int codigoColegio;
    private int codigoPlan;
    private int codigoActividad;
    private String nombreArchivo;
    private String archivo;
    private String formato;
    private String periodo;
}
