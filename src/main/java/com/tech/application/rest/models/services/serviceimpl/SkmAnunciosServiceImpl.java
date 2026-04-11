package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.application.rest.models.dao.ISkmAnunciosDao;
import com.tech.application.rest.models.entity.ISkmAnuncios;
import com.tech.application.rest.models.entity.SkmAnuncios;
import com.tech.application.rest.models.services.service.ISkmAnunciosService;

@Service
public class SkmAnunciosServiceImpl implements ISkmAnunciosService{
    
    @Autowired
    private ISkmAnunciosDao skmAnunciosDao;

    @Override
	@Transactional()	
	public SkmAnuncios BuscarById(Integer id){
		return skmAnunciosDao.BuscarById(id);
	}

    @Override
	@Transactional()	
	public List<SkmAnuncios> BuscarAll() {
        return skmAnunciosDao.findAll();
	}

    @Override
	@Transactional()	
	public List<SkmAnuncios> BuscarByEmpresa(String empresa) {
        return skmAnunciosDao.BuscarByEmpresa(empresa);
	}

	@Override
	@Transactional
	public SkmAnuncios save(SkmAnuncios anuncios) {
		return skmAnunciosDao.save(anuncios);
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		skmAnunciosDao.deleteById(id);
	}

	@Override
	@Transactional()	
	public List<ISkmAnuncios> ConfiguraAnuncioTodos(Integer estado) {
        return skmAnunciosDao.execProcConsultaAnuncioTodos(estado);
	}

	@Override
	@Transactional()	
	public List<ISkmAnuncios> ConfiguraAnuncioAlerta(String usuario,String empresa,Integer alcance,String fecha) {
        return skmAnunciosDao.execProcConsultaAnuncioAlerta(usuario,empresa,alcance,fecha);
	}

	@Override
	@Transactional()	
	public Integer RegistraVisualizacion(Integer anuncio,String usuario,String empresa) {
        return skmAnunciosDao.execProcRegistraAnuncioVisualizacion(anuncio,usuario,empresa);
	}

}
