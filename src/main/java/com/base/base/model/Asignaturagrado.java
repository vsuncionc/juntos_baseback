package com.base.base.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asignaturagrado {
    private int asigaturagrad;
    private int gradoiiee;
    private int asignatura;
    private String cdescripcion;
    private Date fcreacion;
    private Date factualiza;
    private int nestado;
    private String cusucrea;
    private String cusuact;
    private String vigente;

}
