package com.base.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadPlanColegio {
  private int CODIGO;
  private String CTITULO;
  private String  CDESCRIPCION;
  private int DPLANANUALIIEE_PK;
  private String   CNOMBRE;
  private String CFORMATO;
  private String ESTADO;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private String  FCREACION;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private String FACTUALIZA;
  private int PLANANUALIIEE_ID;
  private int ACTIVIDAD_ID;
  private String CUSUCREA;
  private String CUSUACT;
  private String CPERIODO;
}
