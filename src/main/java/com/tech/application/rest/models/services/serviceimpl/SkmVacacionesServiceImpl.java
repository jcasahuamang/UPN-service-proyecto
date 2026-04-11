package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.application.rest.models.dao.ISkmVacacionesDao;
import com.tech.application.rest.models.entity.IRelacionVacaciones;
import com.tech.application.rest.models.entity.SkmVacaciones;
import com.tech.application.rest.models.entity.ValidacionRespuesta;
import com.tech.application.rest.models.services.service.ISkmVacacionesService;

@Service
public class SkmVacacionesServiceImpl implements ISkmVacacionesService {

    @Autowired
    private ISkmVacacionesDao skmVacacionesDao;
    
    @Override
    public SkmVacaciones BuscarById(Long id) {
        return skmVacacionesDao.BuscarById(id);
    }

    @Override
    public List<SkmVacaciones> BuscarAll() {
        return skmVacacionesDao.BuscarAll();  
    }

    @Override
    public List<SkmVacaciones> BuscarByEmpresa(String empresa) {
        return skmVacacionesDao.BuscarByEmpresa(empresa);
    }

    @Override
    public List<SkmVacaciones> BuscarByEmpresaPersonal(String empresa, String personal) {
        return skmVacacionesDao.BuscarByEmpresaPersonal(empresa, personal);
    }

    
    @Override
	@Transactional(readOnly = true)	
	public List<ValidacionRespuesta> ProcValidaVacaciones(String empresa,String personal,String tiporegistro,Long idvac,String fecinicio,String fecfin) {
        return skmVacacionesDao.execProcValidaVacaciones(empresa,personal,tiporegistro,idvac,fecinicio,fecfin);
	}

    @Override
	@Transactional(readOnly = true)	
	public List<IRelacionVacaciones> ProcConsultaVacaciones(String empresa,String planilla,String estado,String personal,String usuario) {
        return skmVacacionesDao.execConsultaVacaciones(empresa,planilla,estado, personal,usuario);
	}

	@Override
	@Transactional
	public SkmVacaciones save(SkmVacaciones vacaciones) {
		return skmVacacionesDao.save(vacaciones);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		skmVacacionesDao.deleteById(id);
	}

}
