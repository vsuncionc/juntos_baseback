package com.base.base.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaPlanAnualRequest
{
    @NotEmpty(message = "El titulo es requerido.")
    @Size(min = 2, max = 100, message = "Tiene que ingresar mas de 2 caracteres.")
    private String  titulo;
    private String descripcion;
    private String periodo;
    private int anio;
    private String mes;
    private int numeroPreguntas;
}
