package com.tech.application.rest.models.services.service;

import java.util.List;

import com.tech.application.rest.models.entity.SkmTabla;

public interface ISkmTablaService {
    public SkmTabla BuscarById(Long id);

    public List<SkmTabla> BuscarAll();

    public List<SkmTabla> BuscarByEmpresa(String empresa);

    public List<SkmTabla> BuscarByEmpresaTabla(String empresa, String tabla);
    
}
