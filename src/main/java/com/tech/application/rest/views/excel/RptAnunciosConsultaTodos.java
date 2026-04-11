package com.tech.application.rest.views.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tech.application.rest.models.entity.ISkmAnuncios;


public class RptAnunciosConsultaTodos {
	static String SHEET = "Reporte";

	 public static ByteArrayInputStream exportarExcel(List<ISkmAnuncios> rptVisualiza) {
		 
		 try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			 
			 
		    	XSSFSheet sheet = workbook.createSheet(SHEET);
		    	XSSFRow row1 = sheet.createRow(0);
		    	
			      row1.createCell(0).setCellValue("ID");
			      row1.createCell(1).setCellValue("TITULO");
			      row1.createCell(2).setCellValue("DESCRIPCION");
			      row1.createCell(3).setCellValue("ESTADO");
			      row1.createCell(4).setCellValue("ALCANCE");
			      row1.createCell(5).setCellValue("FEC. INI. VIGENCIA");
			      row1.createCell(6).setCellValue("FEC. FIN VIGENCIA");
			      row1.createCell(7).setCellValue("CONTENIDO");
			      row1.createCell(8).setCellValue("VISUALIZADO POR");
			      row1.createCell(9).setCellValue("USUARIO");
			      row1.createCell(10).setCellValue("FEC. CREACION");                  					
				  
                   /*
				  CellStyle dateCellStyle = workbook.createCellStyle();
        			CreationHelper createHelper = workbook.getCreationHelper();
        			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy hh:mm:ss"));
                    */
			      int IdRow = 1;
			      for (ISkmAnuncios visualiza : rptVisualiza) {
			    	  XSSFRow row = sheet.createRow(IdRow++);
				        sheet.autoSizeColumn(IdRow);
				        
				        if (visualiza.getid() != null)  row.createCell(0).setCellValue(visualiza.getid());
				        if (visualiza.gettitulo() != null)  row.createCell(1).setCellValue(visualiza.gettitulo());
				        if (visualiza.getdescripcion() != null)  row.createCell(2).setCellValue(visualiza.getdescripcion());
				        if (visualiza.getestado() != null)  row.createCell(3).setCellValue(visualiza.getestado());
				        if (visualiza.getalcance() != null)  row.createCell(4).setCellValue(visualiza.getalcance());
				        if (visualiza.getfecinivigencia() != null)  row.createCell(5).setCellValue(visualiza.getfecinivigencia());
				        if (visualiza.getfecfinvigencia() != null)  row.createCell(6).setCellValue(visualiza.getfecfinvigencia());
                        if (visualiza.getcontenido() != null)  row.createCell(7).setCellValue(visualiza.getcontenido());
                        if (visualiza.getempresa() != null)  row.createCell(8).setCellValue(visualiza.getempresa());
                        if (visualiza.getusucreacion() != null)  row.createCell(9).setCellValue(visualiza.getusucreacion());
                        if (visualiza.getfeccreacion() != null)  row.createCell(10).setCellValue(visualiza.getfeccreacion());

                        //row.createCell(9).setCellStyle(dateCellStyle);
//						row.getCell(9).setCellStyle(dateCellStyle);
					}			      
			      
		    	  //Ajustando columnas
			      int ajuste = 0;    
			      do{    
    		    	  sheet.autoSizeColumn(ajuste);		  
    		    	  ajuste++;    
			      }while(ajuste <= 11);   			      
			      
			 workbook.write(out);
			 return new ByteArrayInputStream(out.toByteArray());
		 } catch (IOException e) {
			 throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		 }
		 
	 }
	 
    
}
