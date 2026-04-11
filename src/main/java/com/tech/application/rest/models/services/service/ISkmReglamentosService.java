package com.tech.application.rest.models.services.service;

import java.util.List;

import com.tech.application.rest.models.entity.SkmReglamentos;

public interface ISkmReglamentosService {
        
        public SkmReglamentos BuscarById(Integer id);

        public List<SkmReglamentos> BuscarAll();
        public List<SkmReglamentos> BuscarByEmpresa(String empresa);

}
