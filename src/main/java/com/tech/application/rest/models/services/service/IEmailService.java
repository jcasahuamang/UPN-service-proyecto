package com.tech.application.rest.models.services.service;

import java.util.Map;

import com.tech.application.rest.models.entity.EmailRequest;
import com.tech.application.rest.models.entity.EmailResponse;


public interface IEmailService {
	public EmailResponse sendEmail(EmailRequest request, Map<String, Object> model);
}
