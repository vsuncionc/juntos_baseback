package com.base.base.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaRequest {
    private int codigoevaluacion;
    private String nombrepregunta; ;
}
