package com.base.base.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaColegiosRequest {
    private String nombre;
    private String descripcion;
    private String depa;
    private String prov;
    private String dist;
    private String poblado;
    private String direccion;
    private String creacion;
    private String actualiza;
    private String estado;
}
