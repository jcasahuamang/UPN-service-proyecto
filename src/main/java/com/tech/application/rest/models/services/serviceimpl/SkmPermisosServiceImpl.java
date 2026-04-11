package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.application.rest.models.dao.ISkmPermisosDao;
import com.tech.application.rest.models.entity.IRelacionPermisos;
import com.tech.application.rest.models.entity.SkmPermisos;
import com.tech.application.rest.models.entity.ValidacionRespuesta;
import com.tech.application.rest.models.services.service.ISkmPermisosService;

@Service
public class SkmPermisosServiceImpl implements ISkmPermisosService{
    
    @Autowired
    private ISkmPermisosDao skmPermisosDao;
    
    @Override
    public SkmPermisos BuscarById(Long id) {
        return skmPermisosDao.BuscarById(id);
    }

    @Override
    public List<SkmPermisos> BuscarAll() {
        return skmPermisosDao.BuscarAll();  
    }

    @Override
    public List<SkmPermisos> BuscarByEmpresa(String empresa) {
        return skmPermisosDao.BuscarByEmpresa(empresa);
    }

    @Override
    public List<SkmPermisos> BuscarByEmpresaPersonal(String empresa, String personal) {
        return skmPermisosDao.BuscarByEmpresaPersonal(empresa, personal);
    }

    
    @Override
	@Transactional(readOnly = true)	
	public List<ValidacionRespuesta> ProcValidaPermisos(String empresa,String personal,String tiporegistro,Long idvac,String fecinicio,String fecfin) {
        return skmPermisosDao.execProcValidaPermisos(empresa,personal,tiporegistro,idvac,fecinicio,fecfin);
	}

    @Override
	@Transactional(readOnly = true)	
	public List<IRelacionPermisos> ProcConsultaPermisos(String empresa,String planilla,String estado,String personal,String usuario) {
        return skmPermisosDao.execConsultaPermisos(empresa,planilla,estado, personal,usuario);
	}

	@Override
	@Transactional
	public SkmPermisos save(SkmPermisos anuncios) {
		return skmPermisosDao.save(anuncios);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		skmPermisosDao.deleteById(id);
	}
}
