package com.tech.application.rest.models.services.service;

import java.util.List;

import com.tech.application.rest.models.entity.SkmAprobaciones;
import com.tech.application.rest.models.entity.ValidacionRespuesta;

public interface ISkmAprobacionesService {
    
    public SkmAprobaciones BuscarById(Long id);

    public List<SkmAprobaciones> BuscarAll();
    public List<ValidacionRespuesta> execProcValidaAprobaciones(String llave,String tipoaprobacion,String accion,String usuario);

    public Integer EjecutaAprobaciones(String llave,String tipoaprobacion,String accion,String usuario);

    public SkmAprobaciones save(SkmAprobaciones aprobaciones);
    public void delete(Long id);

    public Iterable<SkmAprobaciones> saveAll(List<SkmAprobaciones> entities);

	public void deleteAll(List<SkmAprobaciones> entities);	



}
