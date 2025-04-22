package base.juntos.base_back.service.impl;

import base.juntos.base_back.dto.request.PadronBuscarRequest;
import base.juntos.base_back.dto.request.PadronPreCierreRequest;
import base.juntos.base_back.model.*;
import base.juntos.base_back.service.GenerarExcelService;
import base.juntos.base_back.service.PadronService;
import base.juntos.base_back.service.RevaluacionService;
import base.juntos.base_back.service.TablonService;
import base.juntos.base_back.util.UtilReporte;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenerarExcelServiceImpl implements GenerarExcelService {

    private final PadronService padronService;
    private final RevaluacionService revaluacionService;
    private final TablonService tablonService;

    @Override
    public ByteArrayResource listarHogaresGenPadron(PadronBuscarRequest parametros) throws IOException {
        int rowNum = 0;
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("HOGARES GENERADOS");
        var row = sheet.createRow(rowNum);
        var cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "Fecha del Reporte : " + UtilReporte.getDateAndHour());

        //OBTENEMOS INFORMACION DE LA CABEERA
        String usuario ="";
        int cantidadHogares=0;
        BigDecimal monto=new BigDecimal("0");
        List<ResumenGeneracionPadron> informacionCabecera = padronService.resumenPadronGenerado(parametros);
        if(informacionCabecera.size()>0){
            usuario = informacionCabecera.get(0).getUsuarioRegistro();
            cantidadHogares = informacionCabecera.get(0).getCantidadHogares();
            monto = informacionCabecera.get(0).getMontoTotal();
        }


        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "Usuario Genero : " + usuario);


        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "Codigo Padron: " + parametros.getCodigoPadron());

        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "Fecha Generacion : " + UtilReporte.getDateAndHour());


        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "CantidaD Hogares : " + cantidadHogares);

        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "Monto : " + monto.toString());



        // Titulo de las columnas
        rowNum++;
        int columnNum = 0;
        row = sheet.createRow(rowNum);
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "ESQUEMA");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "IDCORTE");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "PERIODO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "IDHOGAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CODIGOHOGAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "MES_1");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "MES_2");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "MONTO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "DOCUMENTO");


        List<HogaresExcelGenPadron> listado = revaluacionService.listaHogaresGneradosPadron(parametros);

        if (listado.isEmpty()) {
            rowNum++;
            columnNum = 0;
            row = sheet.createRow(rowNum);
            cell = row.createCell(columnNum);
            UtilReporte.setTextColumnValueWithoutStyle(cell, "Sin registros para el filtro aplicado.");
        } else {
            for (var item : listado) {

                rowNum++;
                columnNum = 0;
                row = sheet.createRow(rowNum);

                cell = row.createCell(columnNum);
                cell.setCellValue(item.getEsquema());


                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getIdcorte());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getPeriodo());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getIdhogar());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCodigoHogar());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getMes1());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getMes2());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getMonto().toString());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDocumento());


            }

        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        return new ByteArrayResource(bos.toByteArray());
    }

    @Override
    public ByteArrayResource listaHogaresAptos(PadronPreCierreRequest parametros) throws IOException {

        int rowNum = 0;
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("REPORTE PADRON HOGARES APTOS");
        var row = sheet.createRow(rowNum);
        var cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "HOGARES APTOS ");

        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "Fecha del Reporte : " + UtilReporte.getDateAndHour());

        // Titulo de las columnas
        rowNum++;
        int columnNum = 0;
        row = sheet.createRow(rowNum);

        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "UNIDAD TERRITORIAL");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "DEPARTAMENTO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "PROVINCIA");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "DISTRITO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CENTRO_POBLADO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "IDHOGAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CODIGOHOGAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "IDPERSONA");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "DNI");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "NOMBRE TITULAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "APELLIDO PATERNO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "APELLIDO MATERNO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "FFALLECIMIENTO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "FALLECIDO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "ALERTA");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "ESTADO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "X_EXPEDIENTE");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "X_DOCUMENTO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "ID_CORTE");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "IDREEVALUACION");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "COD_PERIODO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CUMPLIMIENTO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "MESES");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CODESTADOHOGAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CUENTA");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "ESTADOCUENTA");

        List<HogaresValidadosAptosExcel> listado = padronService.lsHogaresExcelAptosCierre(parametros);

        if (listado.isEmpty()) {
            rowNum++;
            columnNum = 0;
            row = sheet.createRow(rowNum);
            cell = row.createCell(columnNum);
            UtilReporte.setTextColumnValueWithoutStyle(cell, "Sin registros para el filtro aplicado.");
        } else {

            for (var item : listado) {

                rowNum++;
                columnNum = 0;
                row = sheet.createRow(rowNum);

                cell = row.createCell(columnNum);
                cell.setCellValue(item.getRegion());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDepartamento());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getProvincia());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDistrito());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCentroPoblado());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getIdhogar());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCodigoHogar());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getIdPersona());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDni());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getNombreTitular());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getApellidoPaterno());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getApellidoMaterno());


                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getFechaFallecido());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getFallecido());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getAlerta());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getEstado());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getExpediente());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDocumento());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getIdcorte());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getId());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCodigoPeriodo());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCumplimientoEvaluado());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getMeses());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getEstadoHogar());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCuenta());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getEstadoCuenta());

            }

        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        return new ByteArrayResource(bos.toByteArray());
    }

    @Override
    public ByteArrayResource listaHogaresPreValidadosTodos(PadronPreCierreRequest parametros) throws IOException {

        int rowNum = 0;
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("REPORTE PADRON HOGARES APTOS");
        var row = sheet.createRow(rowNum);
        var cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "HOGARES APTOS ");

        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "Fecha del Reporte : " + UtilReporte.getDateAndHour());

        // Titulo de las columnas
        rowNum++;
        int columnNum = 0;
        row = sheet.createRow(rowNum);

        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "UNIDAD TERRITORIAL");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "DEPARTAMENTO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "PROVINCIA");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "DISTRITO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CENTRO_POBLADO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "PADRON");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "PERIODO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "IDHOGAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "MONTO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "TITULAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "ESQUEMA");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "OBSERVACION");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "VALIDACION");


        List<HogaresValPreCierreTodos> listado = padronService.lsHogaresValPreCierreExcel(parametros);

        if (listado.isEmpty()) {
            rowNum++;
            columnNum = 0;
            row = sheet.createRow(rowNum);
            cell = row.createCell(columnNum);
            UtilReporte.setTextColumnValueWithoutStyle(cell, "Sin registros para el filtro aplicado.");
        } else {

            for (var item : listado) {

                rowNum++;
                columnNum = 0;
                row = sheet.createRow(rowNum);

                cell = row.createCell(columnNum);
                cell.setCellValue(item.getRegion());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDepartamento());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getProvincia());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDistrito());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCentroPoblado());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCodigoPadron());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getPeriodo());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getIdhogar());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getMonto());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getTitular());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getEsquema());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getObservacion());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getValidacion());

            }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        return new ByteArrayResource(bos.toByteArray());
    }

    @Override
    public ByteArrayResource reporteTablon(Long id) throws IOException {

        int rowNum = 0;
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("REPORTE PADRON HOGARES APTOS");
        var row = sheet.createRow(rowNum);
        var cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "HOGARES APTOS ");

        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "Fecha del Reporte : " + UtilReporte.getDateAndHour());

        // Titulo de las columnas
        rowNum++;
        int columnNum = 0;
        row = sheet.createRow(rowNum);

        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "UNIDAD TERRITORIAL");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "DEPARTAMENTO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "PROVINCIA");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "DISTRITO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CENTRO_POBLADO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "IDHOGAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CODIGOHOGAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "IDPERSONA");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "DNI");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "NOMBRE TITULAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "APELLIDO PATERNO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "APELLIDO MATERNO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "FFALLECIMIENTO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "FALLECIDO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "ALERTA");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "ESTADO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "X_EXPEDIENTE");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "X_DOCUMENTO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "ID_CORTE");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "IDREEVALUACION");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "COD_PERIODO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CUMPLIMIENTO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "MESES");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CODESTADOHOGAR");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CUENTA");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "ESTADOCUENTA");

       /* columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "OBSERVACION");*/

        List<HogaresTablonExcel> listado = tablonService.listadoTablonExcel(id);

        if (listado.isEmpty()) {
            rowNum++;
            columnNum = 0;
            row = sheet.createRow(rowNum);
            cell = row.createCell(columnNum);
            UtilReporte.setTextColumnValueWithoutStyle(cell, "Sin registros para el filtro aplicado.");
        } else {

            for (var item : listado) {

                rowNum++;
                columnNum = 0;
                row = sheet.createRow(rowNum);

                cell = row.createCell(columnNum);
                cell.setCellValue(item.getRegion());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDepartamento());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getProvincia());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDistrito());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCentroPoblado());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getIdhogar());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCodigoHogar());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getIdPersona());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDni());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getNombreTitular());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getApellidoPaterno());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getApellidoMaterno());


                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getFechaFallecido());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getFallecido());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getAlerta());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getEstado());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getExpediente());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDocumento());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getIdcorte());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getId());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCodigoPeriodo());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCumplimientoEvaluado());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getMeses());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getEstadoHogar());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCuenta());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getEstadoCuenta());

               /* columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getObservacion());*/

            }

        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        return new ByteArrayResource(bos.toByteArray());
    }
}
