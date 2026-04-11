package com.tech.application.rest.models.services.serviceimpl;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tech.application.rest.models.entity.ISkmAnuncios;
import com.tech.application.rest.models.entity.ISkmVisualizacion;
import com.tech.application.rest.views.excel.RptAnunciosConsultaTodos;
import com.tech.application.rest.views.excel.RptAuditoriaConsultaDoc;

@Service
public class ExcelExportServiceImpl {
 
    	public ByteArrayInputStream exportExcelAuditoria (List<ISkmVisualizacion> visualizacion) {
		ByteArrayInputStream in = RptAuditoriaConsultaDoc.exportarExcel(visualizacion);
		return in;
	}		

	public ByteArrayInputStream exportExcelconsultaAlertaTodos (List<ISkmAnuncios> visualizacion) {
		ByteArrayInputStream in = RptAnunciosConsultaTodos.exportarExcel(visualizacion);
		return in;
	}		

}
