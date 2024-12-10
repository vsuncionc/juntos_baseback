package com.base.base.util;

import com.base.base.dto.response.InformacionArchivo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadUtility {
    private static final String SERVIDOR_ARCHIVO_LINUX="/REPOSITORIO";
    private static final String SERVIDOR_ARCHIVO_WINDOWS="F:\\REPOSITORIO";

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

    private static String calculateDestinationDirectoryArchivo(String codigounico) {
        String anio = Fechas.getAnioString();
        String result = UploadUtility.obtenerRutaServidorArchivo();
        String separador = System.getProperty("file.separator");
        if(separador.equals("/")){ // Linux (/)
            result+="/SEGUIMIENTO/"+anio+"/"+codigounico;
        }else{
            result+="\\SEGUIMIENTO\\"+anio+"\\"+codigounico;
        }

        File dir = new File(result);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        result +=getSeparator();
        return result;
    }

    private  static String calculateDestinationPathArchivo(InformacionArchivo informacionArchivo,String codigounico) {
        Date hoy = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String sFechaHora = formatter.format(hoy);
        String result = calculateDestinationDirectoryArchivo(codigounico);
        //result +=getSeparator();

       // result += informacionArchivo.getNombreArchivo()+"_"+sFechaHora+"."+ FilenameUtils.getExtension(informacionArchivo.getNombreOriginal());
        //informacionArchivo.setNombreArchivoRenombrado(result);
        result += informacionArchivo.getNombreArchivo()+"."+ FilenameUtils.getExtension(informacionArchivo.getNombreOriginal());
        return result;
    }

    public static void saveFileToDiskArchivoSustento(InformacionArchivo informacionArchivo,String codigounico) throws Exception {

        File multipartFile = new File(UploadUtility.calculateDestinationPathArchivo(informacionArchivo,codigounico));

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
