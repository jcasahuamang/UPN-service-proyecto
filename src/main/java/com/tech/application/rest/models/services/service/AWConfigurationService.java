package com.tech.application.rest.models.services.service;

import java.time.Duration;

public interface AWConfigurationService {
    Duration obtenerDuracionBloqueo();

    Integer obtenerMaxAttempts();
}
