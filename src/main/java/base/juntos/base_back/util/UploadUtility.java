package base.juntos.base_back.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UploadUtility {

    private static final String SERVIDOR_ARCHIVO_LINUX="/SRVREPOSITORIOS";
    private static final String SERVIDOR_ARCHIVO_WINDOWS="C:\\Tomcat\\srvArchivo";

    public static String getSeparator(){
        return System.getProperty("file.separator");
    }
    public static String obtenerRutaServidorArchivo(){
        String separador = System.getProperty("file.separator");
        if(separador.equals("/")) // Linux (/)
            return SERVIDOR_ARCHIVO_LINUX;
        else // Windows (\)
            return SERVIDOR_ARCHIVO_WINDOWS;
    }

    private static String calculateDestinationDirectoryArchivo(String nombreArchivo) {
        String result = UploadUtility.obtenerRutaServidorArchivo();
        String separador = System.getProperty("file.separator");
        if(separador.equals("/")){ // Linux (/)
            result+="/GEOREFERENCIA";
        }else{
            result+="\\GEOREFERENCIA";
        }

        result +=getSeparator();
       /* if(!BaseUtility.toStr(idPersona).equals("")){
           result += idPersona;
            result += getSeparator();
        }
        */
        return result;
    }

    private  static String calculateDestinationPathArchivo(InformacionArchivo informacionArchivo) {
        Date hoy = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String sFechaHora = formatter.format(hoy);
        String result = calculateDestinationDirectoryArchivo("GEOREFERENCIA");
        result +=getSeparator();

        result += informacionArchivo.getNombreArchivo()+"_"+sFechaHora+"."+ FilenameUtils.getExtension(informacionArchivo.getNombreOriginal());
        //informacionArchivo.setNombreArchivoRenombrado(result);
        return result;
    }

    public static void saveFileToDiskArchivoSustento(InformacionArchivo informacionArchivo) throws Exception {
        File dir = new File(UploadUtility.calculateDestinationDirectoryArchivo(" "));
        if(!dir.exists()) {
            dir.mkdirs();
        }
       File multipartFile = new File(UploadUtility.calculateDestinationPathArchivo(informacionArchivo));

         informacionArchivo.getArchivo().transferTo(multipartFile);
        informacionArchivo.setRutaArchivo(multipartFile.getAbsolutePath());

    }

    public static boolean isEmptyFile(MultipartFile mpf ){

        if(mpf==null || (mpf!=null && (mpf.getSize()==0 || BaseUtility.toStr(mpf.getOriginalFilename()).equals("") ) ) ){
            return true;
        }
        return false;
    }
}
