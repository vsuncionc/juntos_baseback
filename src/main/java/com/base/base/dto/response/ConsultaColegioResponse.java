package com.base.base.dto.response;

public record ConsultaColegioResponse(
  int codigoColegio,
  String cnombre,
  String  cdescripcion,
  String cdepa,
  String  cprov,
  String cdist,
  String  cpoblado,
  String cdireccion
) { }
