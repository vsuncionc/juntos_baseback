package base.juntos.base_back.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PadronProcesarRequest {
  List<Long> idrevaluaciones;
  int numerohogares;
  int numeromiembrosobjetivos;
  String montopagar;
  String descripcion;
  String codigoUsuario;
  String codigoRevaluacion;
}
