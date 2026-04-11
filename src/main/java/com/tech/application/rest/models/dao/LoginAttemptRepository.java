package com.tech.application.rest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.application.rest.models.entity.LoginAttempt;

@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long>{
 
     // Encuentra un intento de inicio de sesión por la dirección IP
     LoginAttempt findByIpAddress(String ipAddress);

     // Elimina los intentos de inicio de sesión por dirección IP
     void deleteByIpAddress(String ipAddress);
}
