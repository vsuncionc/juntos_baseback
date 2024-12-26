package base.juntos.base_back.dto.response;

public record RevaluacionMhResponse(
        long codigoCorte,
        String codigoPeriodo,
        String tipoEsquema,
        long codigoHogar,
        long idHogar,
        String dni,
        String nombreTitular,
        String cumplioMes1,
        String cumplioMes2
) { }
