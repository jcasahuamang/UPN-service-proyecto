package com.tech.application.rest.models.services.service;

import java.util.List;

import com.tech.application.rest.models.entity.ISkmAnuncios;
import com.tech.application.rest.models.entity.SkmAnuncios;

public interface ISkmAnunciosService {
 
       	public SkmAnuncios BuscarById(Integer id);

        public List<SkmAnuncios> BuscarAll();
        public List<SkmAnuncios> BuscarByEmpresa(String empresa);

        public SkmAnuncios save(SkmAnuncios anuncios);
        public void delete(Integer id);

        public List<ISkmAnuncios> ConfiguraAnuncioTodos(Integer estado);
        public List<ISkmAnuncios> ConfiguraAnuncioAlerta(String usuario,String empresa,Integer alcance,String fecha); 

        public Integer RegistraVisualizacion(Integer anuncio,String usuario,String empresa);
}
