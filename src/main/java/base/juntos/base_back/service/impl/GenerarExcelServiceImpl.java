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
        UtilReporte.setTextTitleCell(cell, "Monto : " + monto.toString()+" S/");



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
        UtilReporte.setTextColumnTitle(cell, "MONTO S/");

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
        Sheet sheet = workbook.createSheet("REPORTE PADRON HOGARES APTOS CIERRE");
        var row = sheet.createRow(rowNum);
        var cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "HOGARES APTOS CIERRE");

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
                cell.setCellValue(item.getIdRevaluacion());

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
        Sheet sheet = workbook.createSheet("REPORTE PADRON HOGARES PRE CIERRE");
        var row = sheet.createRow(rowNum);
        var cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "HOGARES PRE CIERRE");

        rowNum++;
        row = sheet.createRow(rowNum);
        cell = row.createCell(0);
        UtilReporte.setTextTitleCell(cell, "Fecha del Reporte : " + UtilReporte.getDateAndHour());

        // Titulo de las columnas
        rowNum++;
        int columnNum = 0;
        row = sheet.createRow(rowNum);

        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Código de Padrón");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Resolución");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Número de Resolución");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Motivo Pago");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Tipo Padrón");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Período");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "IdHogar");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Código de Hogar");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Monto S/");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Motivo de No Abono");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Unidad Territorial");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Departamento");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Provincia");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Distrito");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Estado del Hogar");
        
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Fecha Desafiliación");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Id Persona");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Apellidos y Nombres");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "DNI");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Número de Cuenta");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Estado de Cuenta");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Fecha de Fallecimiento");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Fallecido");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Observación");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Fecha de Cierre");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Estado Validacion");
        
        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "Observación Validacion");


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
                cell.setCellValue(item.getCodigoPadronPago());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDescripcion());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getNumeroResolucion());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getMotivoPago());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getTipoPadron());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCodigoPeriodo());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getIdhogar());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getCodigoHogar());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getMonto());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getMotivoNoAbono());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getRegion());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDepartamento());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getProvicnia());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDistrito());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getEstadoHogar());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getFechaDesafiliacion());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getIdpersonaTitular());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getNomnbreTitular());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDniTitular());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getNumeroCuentaTitular());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getEstadoCuenta());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getFechaFallecimiento());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getFallecido());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getObservacion());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getFechaCierre());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getEstadoValidacion());
                
                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getObservacionValidacion());

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

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "CODIGO_PUNTOPAGO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "LUGAR_PUNTOPAGO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "DIRECCION_PUNTOPAGO");

        columnNum++;
        cell = row.createCell(columnNum);
        UtilReporte.setTextColumnTitle(cell, "MODALIDAD_PUNTOPAGO");


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
                cell.setCellValue(item.getIdRevaluacion());

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

               columnNum++;
               cell = row.createCell(columnNum);
               cell.setCellValue(item.getCodigoPuntoPagoBanco());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getLugarPuntoPago());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getDireccionPuntoPago());

                columnNum++;
                cell = row.createCell(columnNum);
                cell.setCellValue(item.getModalidadPuntoPago());

            }

        }
        
        
        // CREAMOS LA HOJA DOS SI TENEMOS DATOS
        PadronPreCierreRequest padronPreCierreRequest = new PadronPreCierreRequest();
        padronPreCierreRequest.setCodigoPreValidacionHogar(id);
        List<HogaresValidadoSuspendido> listadoSuspendidos = padronService.lsHogaresSuspendidosCierre(padronPreCierreRequest);
        if(!listadoSuspendidos.isEmpty()) {
        	int rowNum_2 = 0;
        	Sheet sheetNoAptos = workbook.createSheet("REPORTE HOGARES SUSPENDIDOS");
            var row_2 = sheetNoAptos.createRow(rowNum_2);
            var cell_2 = row_2.createCell(0);
            UtilReporte.setTextTitleCell(cell_2, "HOGARES SUSPENDIDOS ");
            
            rowNum_2++;
            row_2 = sheetNoAptos.createRow(rowNum_2);
            cell_2 = row_2.createCell(0);
            UtilReporte.setTextTitleCell(cell_2, "Fecha del Reporte : " + UtilReporte.getDateAndHour());
            
         // Titulo de las columnas
            rowNum_2++;
            int columnNum_2 = 0;
            row_2 = sheetNoAptos.createRow(rowNum_2);

            cell_2 = row_2.createCell(columnNum_2);
            UtilReporte.setTextColumnTitle(cell_2, "DEPARTAMENTO");

            columnNum_2++;
            cell_2 = row_2.createCell(columnNum_2);
            UtilReporte.setTextColumnTitle(cell_2, "PROVINCIA");
            
            columnNum_2++;
            cell_2 = row_2.createCell(columnNum_2);
            UtilReporte.setTextColumnTitle(cell_2, "DISTRITO");
            
            columnNum_2++;
            cell_2 = row_2.createCell(columnNum_2);
            UtilReporte.setTextColumnTitle(cell_2, "CENTRO POBLADO");
            
            columnNum_2++;
            cell_2 = row_2.createCell(columnNum_2);
            UtilReporte.setTextColumnTitle(cell_2, "CODIGO PADRON");
            
            columnNum_2++;
            cell_2 = row_2.createCell(columnNum_2);
            UtilReporte.setTextColumnTitle(cell_2, "CODIGO PERIODO");
            
            columnNum_2++;
            cell_2 = row_2.createCell(columnNum_2);
            UtilReporte.setTextColumnTitle(cell_2, "IDHOGAR");
            
            columnNum_2++;
            cell_2 = row_2.createCell(columnNum_2);
            UtilReporte.setTextColumnTitle(cell_2, "MONTO");
            
            columnNum_2++;
            cell_2 = row_2.createCell(columnNum_2);
            UtilReporte.setTextColumnTitle(cell_2, "TITULAR");
            columnNum_2++;
            cell_2 = row_2.createCell(columnNum_2);
            UtilReporte.setTextColumnTitle(cell_2, "OBSERVACION");
            
            for (var ls  : listadoSuspendidos) {
            	
            	
            	rowNum_2++;
                columnNum_2 = 0;
                row_2 = sheetNoAptos.createRow(rowNum_2);
                
                cell_2 = row_2.createCell(columnNum_2);
                cell_2.setCellValue(ls.getDepartamento());
                
                columnNum_2++;
                cell_2 = row_2.createCell(columnNum_2);
                cell_2.setCellValue(ls.getProvincia());
                
                columnNum_2++;
                cell_2 = row_2.createCell(columnNum_2);
                cell_2.setCellValue(ls.getDistrito());
                
                columnNum_2++;
                cell_2 = row_2.createCell(columnNum_2);
                cell_2.setCellValue(ls.getCentroPoblado());
                
                columnNum_2++;
                cell_2 = row_2.createCell(columnNum_2);
                cell_2.setCellValue(ls.getCodigoPadronPago());
                
                columnNum_2++;
                cell_2 = row_2.createCell(columnNum_2);
                cell_2.setCellValue(ls.getPeriodo());
                
                columnNum_2++;
                cell_2 = row_2.createCell(columnNum_2);
                cell_2.setCellValue(ls.getIdhogar());
                
                columnNum_2++;
                cell_2 = row_2.createCell(columnNum_2);
                cell_2.setCellValue(ls.getMonto());
                
                columnNum_2++;
                cell_2 = row_2.createCell(columnNum_2);
                cell_2.setCellValue(ls.getTitular());
                
                columnNum_2++;
                cell_2 = row_2.createCell(columnNum_2);
                cell_2.setCellValue(ls.getObservacion());
				
			}
            
        	
        }

        // MIEMBROS OBJETIVOS
        List<MiembrosObjetivosHogar> listadoMo=  tablonService.listaMoHogares(id);
        if(!listadoMo.isEmpty()){
            int rowNum_3 = 0;
            Sheet sheetNoAptos = workbook.createSheet("REPORTE MIEMBROS OBJETIVOS");
            var row_3 = sheetNoAptos.createRow(rowNum_3);
            var cell_3 = row_3.createCell(0);
            UtilReporte.setTextTitleCell(cell_3, "IEMBROS OBJETIVOS");

            rowNum_3++;
            row_3 = sheetNoAptos.createRow(rowNum_3);
            cell_3 = row_3.createCell(0);
            UtilReporte.setTextTitleCell(cell_3, "Fecha del Reporte : " + UtilReporte.getDateAndHour());

            // Titulo de las columnas
            rowNum_3++;
            int columnNum_3 = 0;
            row_3 = sheetNoAptos.createRow(rowNum_3);

            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "Nro");

            columnNum_3++;
            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "CODIGOHOGAR");

            columnNum_3++;
            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "IDHOGAR");

            columnNum_3++;
            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "REGION");

            columnNum_3++;
            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "DEPARTAMENTO");

            columnNum_3++;
            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "PROVINCIA");

            columnNum_3++;
            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "DISTRITO");

            columnNum_3++;
            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "CENTRO_POBLADO");

            columnNum_3++;
            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "NOMBRE_COMPLETO");

            columnNum_3++;
            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "CUMPLE_VCC");

            columnNum_3++;
            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "DNI");

            columnNum_3++;
            cell_3 = row_3.createCell(columnNum_3);
            UtilReporte.setTextColumnTitle(cell_3, "IDPERSONA");

            for (var ls  : listadoMo) {
                rowNum_3++;
                columnNum_3 = 0;
                row_3 = sheetNoAptos.createRow(rowNum_3);

                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getId());

                columnNum_3++;
                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getCodigoHogar());

                columnNum_3++;
                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getIdHogar());

                columnNum_3++;
                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getRegion());

                columnNum_3++;
                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getDepartamento());

                columnNum_3++;
                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getProvincia());

                columnNum_3++;
                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getDistrito());

                columnNum_3++;
                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getCentroPoblado());

                columnNum_3++;
                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getNombreMo());

                columnNum_3++;
                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getCumplioVcc());

                columnNum_3++;
                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getDni());

                columnNum_3++;
                cell_3 = row_3.createCell(columnNum_3);
                cell_3.setCellValue(ls.getIdpersona());

            }


        }


        // REPORTE DE MIEMBROS OBJETIVOS TPI
        

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        return new ByteArrayResource(bos.toByteArray());
    }

}
