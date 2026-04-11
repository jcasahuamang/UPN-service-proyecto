package com.tech.application.rest.models.services.serviceimpl;

//import java.time.Duration;
//import java.time.LocalDateTime;
import java.util.List;
//import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.application.rest.models.dao.IUsuarioDao;
//import com.tech.application.rest.models.dao.LoginAttemptRepository;
//import com.tech.application.rest.models.entity.LoginAttempt;
import com.tech.application.rest.models.entity.MaeUsuario;
//import com.tech.application.rest.models.services.service.AWConfigurationService;
import com.tech.application.rest.models.services.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuarioDao;

	/*
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoginAttemptRepository loginAttemptRepository;

	@Autowired
	private AWConfigurationService configuracionService;
	*/

	@Override
	@Transactional(readOnly = true)
	public MaeUsuario findById(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<MaeUsuario> findByUsuario(String usuario) {
        return usuarioDao.findByUsuario(usuario);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<MaeUsuario> findByEmail(String email) {
        return usuarioDao.findByEmail(email);
	}
	
	@Override
	public List<MaeUsuario> findAllByCliente(Integer cliente) {
        return usuarioDao.findAllByCliente(cliente);
	}

	@Override
	public List<MaeUsuario> findAllByCompania(Integer compania) {
        return usuarioDao.findAllByCompania(compania);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean existsByUsuario(String usuario) {
        return usuarioDao.existsByUsuario(usuario);		
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {
        return usuarioDao.existsByEmail(email);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<MaeUsuario> findAll() {
		return (List<MaeUsuario>)usuarioDao.findAll();
	}

	@Override
	@Transactional
	public MaeUsuario save(MaeUsuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		usuarioDao.deleteById(id);
	
	}

	/*
	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyCaptcha(String token, String remoteIp) {
		HttpHeaders headers = new HttpHeaders();

		String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
		String SECRET_KEY = "6LfR640qAAAAAKCS5W5O9Tp-ZYZtkKROjwqbvtLg";

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// Crear el payload para la solicitud
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//		map.add("secret", Constantes.SECRET_KEY);
		map.add("secret", SECRET_KEY);
		map.add("response", token);
		map.add("remoteip", remoteIp);


		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		// Realizar la solicitud POST al endpoint de Google
//		ResponseEntity<String> response = restTemplate.postForEntity(Constantes.RECAPTCHA_VERIFY_URL, request,String.class);
		ResponseEntity<String> response = restTemplate.postForEntity(RECAPTCHA_VERIFY_URL, request,String.class);
		// Registrar la respuesta completa para depuración

		try {
			// Convertir el cuerpo de la respuesta JSON a un Map
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> responseBody = objectMapper.readValue(response.getBody(), Map.class);

			// Obtener el valor de "success"
			return responseBody != null && Boolean.TRUE.equals(responseBody.get("success"));
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean isBlocked(String ip) {
		LoginAttempt attempt = loginAttemptRepository.findByIpAddress(ip);
		return attempt != null && attempt.getLockTime() != null &&
				attempt.getLockTime().isAfter(LocalDateTime.now());
	}


	@Transactional
	@Override
	public void loginFailed(String ip) {
		Duration lockTime = configuracionService.obtenerDuracionBloqueo();
		Integer maxAttempts = configuracionService.obtenerMaxAttempts();

		LoginAttempt attempt = loginAttemptRepository.findByIpAddress(ip);
		if (attempt == null) {
			attempt = new LoginAttempt();
			attempt.setIpAddress(ip);
			attempt.setAttemptCount(1);
		} else {
			attempt.setAttemptCount(attempt.getAttemptCount() + 1);
			if (attempt.getAttemptCount() >= maxAttempts) {
				attempt.setLockTime(LocalDateTime.now().plus(lockTime));
			}
		}
		loginAttemptRepository.save(attempt);
	}
	
	@Transactional
	@Override
	public void loginSucceeded(String ip) {
		loginAttemptRepository.deleteByIpAddress(ip);
	}
	*/
}
