package com.base.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanAnualnstitucion {
    private int plananualiiee_pk;
    private int iiee_pk;
    private int plananula_pk;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String fcreacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String factualiza;
    private int nestado;
    private String cusucrea;
    private String cusuact;
    private String vigente;
    private String colegio;
    private String plan;
    private String cperiodo;
    private String canio;
    private String cmes;
    private String estado;


}
