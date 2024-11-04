package base.juntos.base_back.dto.response;

public record UsuarioResponse(
         String codUsuario,String nombre,String apellidoPaterno, String apellidoMaterno,
         String usuario,String departamento_1,String departamento_2, String clave
) {  }
