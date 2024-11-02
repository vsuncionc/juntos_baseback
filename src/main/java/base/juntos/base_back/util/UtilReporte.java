package base.juntos.base_back.util;


import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilReporte {

    public static void setTextTitleCell(Cell cell, String text) {
        CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
        Font font = cell.getSheet().getWorkbook().createFont();
        font.setBold(true);
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        cell.setCellValue(text);
        cell.setCellStyle(style);
    }

    public static String getDateAndHour() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(formatter);
    }


    public static void setTextColumnTitle(Cell cell, String text) {
        CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
        // alinear al centro vertical y horizontal
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        // Establecer el borde
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        // colorde fondo
        XSSFColor color = new XSSFColor(Color.decode("#C2C2C2"), null);
        style.setFillForegroundColor(color);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font font = cell.getSheet().getWorkbook().createFont();
        font.setBold(true);
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        cell.setCellValue(text);
        cell.setCellStyle(style);
        cell.getSheet().setColumnWidth(cell.getColumnIndex(), 30*256);
        cell.getRow().setHeight((short) (26*20));
    }

    public static void setTextColumnValue(Cell cell, String text) {
        CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
        // Establecer el borde
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        Font font = cell.getSheet().getWorkbook().createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 9);
        style.setFont(font);
        cell.setCellValue(text);
        cell.setCellStyle(style);
    }

    public static void setTextColumnValueWithoutStyle(Cell cell, String text) {
        cell.setCellValue(text);
    }

    public static void setTextColumnValueNumber(Cell cell, Long number) {
        CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
        // Establecer el borde
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        // Establecer el formato de la celda a número sin decimales
        DataFormat format = cell.getSheet().getWorkbook().createDataFormat();
        style.setDataFormat(format.getFormat("0"));

        Font font = cell.getSheet().getWorkbook().createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 9);
        style.setFont(font);
        cell.setCellValue(number);
        cell.setCellStyle(style);
    }
}
