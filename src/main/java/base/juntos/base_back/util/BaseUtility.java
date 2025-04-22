package base.juntos.base_back.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.text.ParseException;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BaseUtility {

    public static String  toStr(Object valor) {
        if(valor==null){
            return "";
        }
        return valor.toString();
    }

    public enum estadoRevaluacion {
        RECIBIDO("RECIBIDO",1),
        PROCESADO("PROCESADO",2);
        private String nombrEstadoRevaluacion;
        private int codigoEstadoRevaluacion;
        private estadoRevaluacion (String nombrEstadoRevaluacion, int codigoEstadoRevaluacion){
            this.nombrEstadoRevaluacion = nombrEstadoRevaluacion;
            this.codigoEstadoRevaluacion = codigoEstadoRevaluacion;
        }
    }

public static double cadenaToDecimal(String monto){
    DecimalFormat formato = new DecimalFormat("#,##0.00"); // Formato sin símbolo de moneda
    double valorMonetario =0L;
    try {
        Number numero = formato.parse(monto);
        valorMonetario = numero.doubleValue();
    } catch (ParseException e) {
        System.err.println("Error al convertir el texto a valor monetario: " + e.getMessage());
    }
    return valorMonetario;
}


}
