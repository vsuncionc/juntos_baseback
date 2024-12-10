package com.base.base.dto.request;

import com.base.base.model.ColegioPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarColegioPlanRequest {

    int codigoPlanAnual;
    List<ColegioPlan> listaColegios;
}
