package com.base.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanAnual {
    private int plananual;
    private String  ctitulo;
    private String cdescripcion;
    private String cperiodo;
    private int  canio;
    private int ncantpreguntas;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String fcreacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String factualiza;
    private int nestado;
    private String cusucrea;
    private String cusuact;
    private String vigente;

}
