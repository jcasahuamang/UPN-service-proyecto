package com.tech.application.rest.models.services.serviceimpl;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.application.rest.models.dao.AWConfigurationRepository;
import com.tech.application.rest.models.entity.AWConfiguration;
import com.tech.application.rest.models.services.service.AWConfigurationService;

@Service
public class AWConfigurationServiceImpl implements AWConfigurationService {
    
    @Autowired
    private AWConfigurationRepository configuracionRepository;

    private static final String DEFAULT_DURATION = "60"; // Valor por defecto en minutos
    private static final String CLAVE_DURACION_BLOQUEO = "DURACION_BLOQUEO";
    private static final String CLAVE_MAX_ATTEMPTS = "MAX_INTENTOS";

    @Override
    public Duration obtenerDuracionBloqueo() {
        // Buscar en la base de datos
        Optional<AWConfiguration> config = configuracionRepository.findByClave(CLAVE_DURACION_BLOQUEO);

        // Si se encuentra, convertir el valor en minutos a Duration
        if (config.isPresent()) {
            try {
                return Duration.ofMinutes(Long.parseLong(config.get().getValor()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new IllegalStateException(
                        "El valor de DURACION_BLOQUEO no es válido: " + config.get().getValor());
            }
        }

        // Si no se encuentra, devolver el valor por defecto
        return Duration.ofMinutes(Long.parseLong(DEFAULT_DURATION));
    }

    @Override
    public Integer obtenerMaxAttempts() {
        Optional<AWConfiguration> config = configuracionRepository.findByClave(CLAVE_MAX_ATTEMPTS);

        if (config.isPresent()) {
            try {
                return Integer.parseInt(config.get().getValor());
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("El valor de MAX_INTENTOS no es válido: " + config.get().getValor());
            }
        }

        return 3;
    }
}
