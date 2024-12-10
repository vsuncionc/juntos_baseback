package com.base.base.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actividad {

    private int  actividad_pk;
    private int plananula_id;
    private String  cdescripcion;
    private LocalDate fapertura;
    private LocalDate fcierre;
    private LocalDate fcreacion;
    private LocalDate factualiza;
    private int nestado;
    private String cusucrea;
    private String cusuact;
    private String descripcionEstado;
    private String  vigente;

}
