package com.base.base.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public record ConsultaPlanAnualResponse (
     int plananual,
     String  titulo,
     String descripcion,
     String periodo,
     int  anio,
     int cantidadpreguntas,
     String fechacreacion
){}
