package com.base.base.dto.response;

public record ActividadColegioConsulta(
     int codigo,
     String nombrePlan,
     String nombreActividad,
     String nombreArchivo,
     String estadoApertura,
     String fechaCreacion,
     String fechaActualizacion
) {
}
