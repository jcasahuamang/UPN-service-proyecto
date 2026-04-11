package com.tech.application.rest.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.application.rest.security.entity.Rol;
import com.tech.application.rest.security.enums.RolNombre;
import com.tech.application.rest.security.repository.RolRepository;

@Service
@Transactional
public class RolService {
	
	@Autowired
	RolRepository rolRepository;
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		return rolRepository.findByRolNombre(rolNombre);		
	}
	
    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
