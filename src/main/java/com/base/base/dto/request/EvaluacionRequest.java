package com.base.base.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluacionRequest {
    private String nombre;
    private String descripcion;
    private String fechApertura;
    private String fechaCierre;
    private int tiempoDuracion;
    private String periodo;
    private String anio;
    private int cantidadPreguntas;
}
