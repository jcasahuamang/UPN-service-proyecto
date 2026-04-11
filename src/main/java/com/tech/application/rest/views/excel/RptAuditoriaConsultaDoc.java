package com.tech.application.rest.views.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.tech.application.rest.models.entity.ISkmVisualizacion;

import org.apache.poi.ss.usermodel.CellStyle;

public class RptAuditoriaConsultaDoc {
    
	static String SHEET = "Reporte";

	 public static ByteArrayInputStream exportarExcel(List<ISkmVisualizacion> rptVisualiza) {
		 
		 try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			 
			 
		    	XSSFSheet sheet = workbook.createSheet(SHEET);
		    	XSSFRow row1 = sheet.createRow(0);
		    	
			      row1.createCell(0).setCellValue("COD. EMPRESA");
			      row1.createCell(1).setCellValue("NOMBRE EMPRESA");
			      row1.createCell(2).setCellValue("PLANILLA");
			      row1.createCell(3).setCellValue("AÑO");
			      row1.createCell(4).setCellValue("MES");
			      row1.createCell(5).setCellValue("COD. PERSONAL");
			      row1.createCell(6).setCellValue("NOMBRE TRABAJADOR");
			      row1.createCell(7).setCellValue("NUM DOC IDENTIDAD");
			      row1.createCell(8).setCellValue("TIPO DOCUMENTO");
			      row1.createCell(9).setCellValue("FEC. VISUALIZACION");
					
				  /*
				  CreationHelper createHelper = workbook.getCreationHelper();
			      short format = createHelper.createDataFormat().getFormat("dd/mm/yyyy");
			      CellStyle cellStyle = workbook.createCellStyle();	
			      cellStyle.setDataFormat(format);
					*/
				  CellStyle dateCellStyle = workbook.createCellStyle();
        			CreationHelper createHelper = workbook.getCreationHelper();
        			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy hh:mm:ss"));

			      int IdRow = 1;
			      for (ISkmVisualizacion visualiza : rptVisualiza) {
			    	  XSSFRow row = sheet.createRow(IdRow++);
				        sheet.autoSizeColumn(IdRow);
				        
				        if (visualiza.getcodempresa() != null)  row.createCell(0).setCellValue(visualiza.getcodempresa());
				        if (visualiza.getnombreempresa() != null)  row.createCell(1).setCellValue(visualiza.getnombreempresa());
				        if (visualiza.getdesplanilla() != null)  row.createCell(2).setCellValue(visualiza.getdesplanilla());
				        if (visualiza.getanoperiodo() != null)  row.createCell(3).setCellValue(visualiza.getanoperiodo());
				        if (visualiza.getmesperiodo() != null)  row.createCell(4).setCellValue(visualiza.getmesperiodo());
				        if (visualiza.getcodpersonal() != null)  row.createCell(5).setCellValue(visualiza.getcodpersonal());
				        if (visualiza.getnomtrabajador() != null)  row.createCell(6).setCellValue(visualiza.getnomtrabajador());
                        if (visualiza.getnumdocidentidad() != null)  row.createCell(7).setCellValue(visualiza.getnumdocidentidad());
                        if (visualiza.gettipodocumento() != null)  row.createCell(8).setCellValue(visualiza.gettipodocumento());
                        if (visualiza.getfechavisualizacion() != null)  row.createCell(9).setCellValue(visualiza.getfechavisualizacion());
						//row.createCell(9).setCellStyle(dateCellStyle);
						row.getCell(9).setCellStyle(dateCellStyle);
					}			      
			      
		    	  //Ajustando columnas
			      int ajuste = 0;    
			      do{    
    		    	  sheet.autoSizeColumn(ajuste);		  
    		    	  ajuste++;    
			      }while(ajuste <= 10);   			      
			      
			 workbook.write(out);
			 return new ByteArrayInputStream(out.toByteArray());
		 } catch (IOException e) {
			 throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		 }
		 
	 }
	 

}
