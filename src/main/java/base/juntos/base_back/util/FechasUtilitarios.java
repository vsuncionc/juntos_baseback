package base.juntos.base_back.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class FechasUtilitarios {

    public static String obtenerFechaHora() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(formatter);
    }


    public static String obtenerFecha() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return now.format(formatter);
    }

    public static String obtenerHora() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return now.format(formatter);
    }

    public static String obtenerFechaHoraTexto() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a");
        return now.format(formatter);
    }


    public static String obtenerFechaTexto() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return now.format(formatter);
    }

    public static String obtenerFechaHoraJunto() {
        Random randomNumbers = new Random();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nombreArchivo = now.format(formatter);
        nombreArchivo = "PADRON_"+nombreArchivo+randomNumbers.nextInt()+".xlsx";
        return nombreArchivo;
    }
}
