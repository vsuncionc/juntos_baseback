package base.juntos.base_back.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class InformacionArchivo {
    private final String nombreOriginal;
    private final String tipoContenido;
    private final long peso;
    private final byte[] archivoBytes;
    private MultipartFile archivo;
    private String extension;
    private String nombreArchivo;
    private String rutaArchivo;
    public InformacionArchivo(MultipartFile file) {
        this.nombreOriginal = file.getOriginalFilename();
        this.nombreArchivo  = file.getOriginalFilename().replaceFirst("[.][^.]+$", "").toUpperCase();
        this.tipoContenido  = file.getContentType();
        this.peso = file.getSize();
        this.archivo = file;
        this.extension = FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            this.archivoBytes = file.getBytes();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los bytes del archivo", e);
        }
    }


}
