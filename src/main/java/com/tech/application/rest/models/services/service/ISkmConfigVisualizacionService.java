package com.tech.application.rest.models.services.service;

import java.util.List;

import com.tech.application.rest.models.entity.ISkmPeriodoVisualizacion;
import com.tech.application.rest.models.entity.ISkmTipoPlanilla;
import com.tech.application.rest.models.entity.ISkmVisualizacion;

public interface ISkmConfigVisualizacionService {

    
    public Integer RegistraVisualizacion(String codempresa,String anoproceso,String mesproceso,String codpersonal,String codusuario,String numdocidentidad,String tipdocumento);

    public List<ISkmVisualizacion> ConsultaVisualizacion(String codempresa,String planilla,String anoproceso,String mesproceso,String tipdocumento);

    public Integer ConfiguraVisualizacion(String codempresa,String anoproceso,String mesproceso,String codtipoplanilla,String tipdocumento,String accion);

    public List<ISkmPeriodoVisualizacion> PeriodoVisualizacion(String codempresa,String tipoplanilla,String tipdocumento,String anoproceso);

    public List<ISkmTipoPlanilla> TipoPlanilla(String codempresa);

    public Integer ActualizaUsuario(String usuario,String claveant,String clavenuevo);

}
