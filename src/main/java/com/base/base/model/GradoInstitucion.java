package com.base.base.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradoInstitucion {
    private int gradoiiee;
    private int cicloiiee;
    private int grado;
    private String  cdescripcion;
    private Date fcreacion;
    private Date factualiza;
    private int nestado;
    private String cusucrea;
    private String cusuact;
    private String vigente;

}
