package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.application.rest.models.dao.ISkmReglamentosDao;
import com.tech.application.rest.models.entity.SkmReglamentos;
import com.tech.application.rest.models.services.service.ISkmReglamentosService;

@Service
public class SkmReglamentosServiceImpl implements ISkmReglamentosService {

    @Autowired
    private ISkmReglamentosDao skmReglamentosDao;

    @Override
    @Transactional()    
    public SkmReglamentos BuscarById(Integer id) {
        return skmReglamentosDao.BuscarById(id);
    }

    @Override
    @Transactional()    
    public List<SkmReglamentos> BuscarAll() {
        return skmReglamentosDao.findAll();
    }

    @Override
    @Transactional()    
    public List<SkmReglamentos> BuscarByEmpresa(String empresa) {
        return skmReglamentosDao.BuscarByEmpresa(empresa);
    }
    
}
