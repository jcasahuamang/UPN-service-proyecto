package com.tech.application.rest.models.services.service;

import java.util.List;

import com.tech.application.rest.models.entity.IRelacionVacaciones;
import com.tech.application.rest.models.entity.SkmVacaciones;
import com.tech.application.rest.models.entity.ValidacionRespuesta;

public interface ISkmVacacionesService {
    public SkmVacaciones BuscarById(Long id);

    public List<SkmVacaciones> BuscarAll();
    
    public List<SkmVacaciones> BuscarByEmpresa(String empresa);
    
    public List<SkmVacaciones> BuscarByEmpresaPersonal(String empresa, String personal);

    public List<ValidacionRespuesta> ProcValidaVacaciones(String empresa,String personal,String tiporegistro,Long idvac,String fecinicio,String fecfin);

    public List<IRelacionVacaciones> ProcConsultaVacaciones(String empresa,String planilla,String estado,String personal,String usuario);


    public SkmVacaciones save(SkmVacaciones vacaciones);
    public void delete(Long id);


}
