package base.juntos.base_back.dto.response;

public record RevaluacionPadron(
   long item,
    String codigoRevaluacion,
   String tipoEsquema,
   String  expediente ,
   String  documento ,
   String  fechaProceso,
   String  codigoPadron,
   String  cantidadHogares
) {
}
