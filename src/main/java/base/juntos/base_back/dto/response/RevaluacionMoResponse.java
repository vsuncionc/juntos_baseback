package base.juntos.base_back.dto.response;

public record RevaluacionMoResponse (
        long item,
        long codigoCorte,
        String codigoPeriodo,
        String tipoEsquema,
        long codigoHogar,
        long idHogar,
        String dniTitular,
        String nombre,
        String mes_1,
        String mes_2
){
}
