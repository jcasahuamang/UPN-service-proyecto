package com.tech.application.rest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.application.rest.models.entity.EmailRequest;
import com.tech.application.rest.models.entity.EmailResponse;
import com.tech.application.rest.models.services.service.IEmailService;



@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/email")
public class EmailController {


	Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private IEmailService emailService;

	@PostMapping("/confirmaregistro")
	public EmailResponse sendEmail(@RequestBody EmailRequest request) {
		request.setFrom("jcasahuamang@gmail.com");
		Map<String, Object> model = new HashMap<>();
		model.put("Nombre", request.getName());
		//model.put("Ubicacion", "Peru-Casa");
		
		return emailService.sendEmail(request, model);
	}
	
}
