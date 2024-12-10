package com.base.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluacion {
    private  int  evaluacion;
    private  String ctitulo;
    private String cdescripcion;
    private  Date fcreacion;
    private  Date fapertura;
    private  Date fcierre;
    private int nduracion;
    private String cperiodo;
    private int canio;
    private int ncantPreguntas;
    private Date factualiza;
    private int nestado;
    private String cusucrea;
    private String cusuact;
    private String vigente;

}
