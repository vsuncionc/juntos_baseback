package com.base.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    private int persona;
    private String cnombre;
    private String cpaterno;
    private String cmaterno;
    private String cgenero;
    private Date   dfnacimiento;
    private String cdepa;
    private String cprov;
    private String cdist;
    private String cpoblado;
    private String cdireccion;
    private Date fcreacion;
    private Date factualiza;
    private int nestado;
    private String cusucrea;
    private String cusuact;
    private String vigente;

}
