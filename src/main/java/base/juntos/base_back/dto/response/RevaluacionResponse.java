package base.juntos.base_back.dto.response;


public record RevaluacionResponse (
 long id,
 String expediente,
 String documento,
 String fechaDocumento,
 String fechaProceso,
 String descripcion,
 String estado,
 int cantidadHogares,
 String abonoGnerado,
 String tipoPago,
 String tipoEsquema
){
}
