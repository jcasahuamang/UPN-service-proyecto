package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.application.rest.models.dao.ISkmPrestamosDao;
import com.tech.application.rest.models.entity.IRelacionPrestamos;
import com.tech.application.rest.models.entity.SkmPrestamos;
import com.tech.application.rest.models.entity.ValidacionRespuesta;
import com.tech.application.rest.models.services.service.ISkmPrestamosService;

@Service
public class SkmPrestamosServiceImpl implements ISkmPrestamosService {

    @Autowired
    private ISkmPrestamosDao skmPrestamosDao;
    
    @Override
    public SkmPrestamos BuscarById(Long id) {
        return skmPrestamosDao.BuscarById(id);
    }

    @Override
    public List<SkmPrestamos> BuscarAll() {
        return skmPrestamosDao.BuscarAll();  
    }

    @Override
    public List<SkmPrestamos> BuscarByEmpresa(String empresa) {
        return skmPrestamosDao.BuscarByEmpresa(empresa);
    }

    @Override
    public List<SkmPrestamos> BuscarByEmpresaPersonal(String empresa, String personal) {
        return skmPrestamosDao.BuscarByEmpresaPersonal(empresa, personal);
    }

    
    @Override
	@Transactional(readOnly = true)	
	public List<ValidacionRespuesta> ProcValidaPrestamos(String empresa,String personal,String tiporegistro,Long id,String fecsolicitud,String moneda,Double importe,Integer cuota) {
        return skmPrestamosDao.execProcValidaPrestamos( empresa, personal, tiporegistro,id, fecsolicitud,moneda, importe,cuota);
	}

    @Override
	@Transactional(readOnly = true)	
	public List<IRelacionPrestamos> ProcConsultaPrestamos(String empresa,String planilla,String estado,String personal,String usuario) {
        return skmPrestamosDao.execConsultaPrestamos(empresa,planilla,estado, personal,usuario);
	}   
    
	@Override
	@Transactional
	public SkmPrestamos save(SkmPrestamos prestamos) {
		return skmPrestamosDao.save(prestamos);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		skmPrestamosDao.deleteById(id);
	}
    
}
