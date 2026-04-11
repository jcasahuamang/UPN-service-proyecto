package com.tech.application.rest.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.application.rest.models.entity.AWConfiguration;

public interface AWConfigurationRepository extends JpaRepository<AWConfiguration, Long> {
    Optional<AWConfiguration> findByClave(String clave);
}
