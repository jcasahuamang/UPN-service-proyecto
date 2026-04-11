package com.tech.application.rest.models.services.service;

import java.util.List;

import com.tech.application.rest.models.entity.IRelacionPrestamos;
import com.tech.application.rest.models.entity.SkmPrestamos;
import com.tech.application.rest.models.entity.ValidacionRespuesta;

public interface ISkmPrestamosService {
    public SkmPrestamos BuscarById(Long id);

    public List<SkmPrestamos> BuscarAll();
    
    public List<SkmPrestamos> BuscarByEmpresa(String empresa);
    
    public List<SkmPrestamos> BuscarByEmpresaPersonal(String empresa, String personal);

    public List<ValidacionRespuesta> ProcValidaPrestamos(String empresa,String personal,String tiporegistro,Long id,String fecsolicitud,String moneda,Double importe,Integer cuota);

    public List<IRelacionPrestamos> ProcConsultaPrestamos(String empresa,String planilla,String estado,String personal,String usuario);


    public SkmPrestamos save(SkmPrestamos prestamos);
    public void delete(Long id);

}
