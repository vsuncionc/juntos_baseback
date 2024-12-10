package com.base.base.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultActividadRequest {
    private int codigoPlanAnual;
    private String  nombrePlanAnual;
    private String descripcionActividad;
    private String periodo;
    private int anio;
    private String mes;
}
