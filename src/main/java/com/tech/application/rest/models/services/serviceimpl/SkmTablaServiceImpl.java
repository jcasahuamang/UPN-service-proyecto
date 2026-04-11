package com.tech.application.rest.models.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.application.rest.models.dao.ISkmTablaDao;
import com.tech.application.rest.models.entity.SkmTabla;
import com.tech.application.rest.models.services.service.ISkmTablaService;

@Service
public class SkmTablaServiceImpl implements ISkmTablaService {

    @Autowired
    private ISkmTablaDao skmTablaDao;

    @Override
    public SkmTabla BuscarById(Long id) {
        return skmTablaDao.BuscarById(id);
    }

    @Override
    public List<SkmTabla> BuscarAll() {
        return skmTablaDao.BuscarAll();
    }

    @Override
    public List<SkmTabla> BuscarByEmpresa(String empresa) {
        return skmTablaDao.BuscarByEmpresa(empresa);
    }

    @Override
    public List<SkmTabla> BuscarByEmpresaTabla(String empresa, String tabla) {
        return skmTablaDao.BuscarByEmpresaTabla(empresa, tabla);
    }

    
}