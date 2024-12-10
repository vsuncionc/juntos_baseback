package com.base.base.model;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvalAsigAlumno {
    private int evalasignatura;
    private int evaluacion;
    private int asigaturagra;
    private int alumnoGrado;
    private int ncantPreguntas;
    private int ncantCorrectas;
    private int ncantIncorrectas;
    private LocalDateTime nhoraInicio;
    private LocalDateTime nhoraFin;
    private Date fcreacion;
    private Date factualiza;
    private int nestado;
    private String cusucrea;
    private String cusuact;
    private String vigente;

}
