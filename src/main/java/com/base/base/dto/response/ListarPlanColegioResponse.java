package com.base.base.dto.response;

public record ListarPlanColegioResponse(
    int codigoplaniiee,
    int codigoplan,
    int codigocolegio,
    String nombreColegio,
    String nombrePlan,
    String estadoPlan,
    String periodo,
    String anio,
    String mes,
    String fechaCreacion
) {
}
