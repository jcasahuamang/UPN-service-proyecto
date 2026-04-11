package com.tech.application.rest.models.services.serviceimpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.tech.application.rest.models.entity.EmailRequest;
import com.tech.application.rest.models.entity.EmailResponse;
import com.tech.application.rest.models.services.service.IEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	private Configuration freemarkerConfig;

	@Override
	public EmailResponse sendEmail(EmailRequest request, Map<String, Object> model) {

		EmailResponse response =  new EmailResponse();
		MimeMessage message = emailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			
//			helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
			
			Template t = freemarkerConfig.getTemplate("email-confirmacion-registro.ftl"); 
			
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			
			helper.setTo(request.getTo());
			helper.setText(html, true);
			helper.setSubject(request.getSubject());
			helper.setFrom(request.getFrom());
			
			emailSender.send(message);
			
			response.setMessage("email send to : " + request.getTo());
			response.setStatus(Boolean.TRUE);
				
			
		} catch (MessagingException | IOException | TemplateException e) {
			response.setMessage("Mail sending failure : " + e.getMessage());
			response.setStatus(Boolean.FALSE);
		}
		
		
		return response;
	}
	
}
