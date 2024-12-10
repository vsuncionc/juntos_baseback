package com.base.base.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroPlanAnualActividad {
  private int codigoPlanAnual;
  private String nombreActividad;
  private List<Integer> listaActividades;
}
