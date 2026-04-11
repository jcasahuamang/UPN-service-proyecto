package com.tech.application.rest.models.services.service;

import java.util.List;

import com.tech.application.rest.models.entity.IRelacionPermisos;
import com.tech.application.rest.models.entity.SkmPermisos;
import com.tech.application.rest.models.entity.ValidacionRespuesta;

public interface ISkmPermisosService {
    
    public SkmPermisos BuscarById(Long id);

    public List<SkmPermisos> BuscarAll();
    
    public List<SkmPermisos> BuscarByEmpresa(String empresa);
    
    public List<SkmPermisos> BuscarByEmpresaPersonal(String empresa, String personal);

    public List<ValidacionRespuesta> ProcValidaPermisos(String empresa,String personal,String tiporegistro,Long idvac,String fecinicio,String fecfin);

    public List<IRelacionPermisos> ProcConsultaPermisos(String empresa,String planilla,String estado,String personal,String usuario);

    public SkmPermisos save(SkmPermisos permisos);
    public void delete(Long id);
}
