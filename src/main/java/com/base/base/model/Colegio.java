package com.base.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Colegio {
    private  int iiee_pk;
    private  String cnombre;
    private  String  cdescripcion;
    private String cdepa;
    private String  cprov;
    private String cdist;
    private String  cpoblado;
    private String cdireccion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date fcreacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date factualiza;
    private int nestado;
    private String cusucrea;
    private String cusuact;
    private String vigente;

}
