package base.juntos.base_back.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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




}
