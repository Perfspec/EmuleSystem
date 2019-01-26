package com.infosys.emailapi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.emailapi.constants.Constant;
import com.infosys.emailapi.persistence.domain.Email;
import com.infosys.emailapi.persistence.domain.User;
import com.infosys.emailapi.service.EmailServicible;

@RestController
@RequestMapping(path=Constant.EMAIL_PATH)
public class EmailController {
	
	@Autowired
	private EmailServicible emailService;
	
	@GetMapping
	public List<Email> getByUsername(
			@PathVariable(name=Constant.USER_COLUMN_USERNAME_NAME) String username) {
		return emailService.getByUsername(username);
		
	}
	
	@PatchMapping
	public String deleteEmail(
			@PathVariable(name=Constant.EMAIL_COLUMN_ID_NAME) Long id) {
		return emailService.deleteEmail(id);
	}
	
	@PostMapping
	public String composeEmail(
			@RequestParam(name=Constant.EMAIL_COLUMN_USER_FROM_NAME) User fromUser,
			@RequestParam(name=Constant.EMAIL_COLUMN_USER_TO_NAME) User toUser,
			@RequestParam(name=Constant.EMAIL_COLUMN_SUBJECT_NAME) String subject,
			@RequestParam(name=Constant.EMAIL_COLUMN_BODY_NAME) String body) {
		return emailService.composeEmail(fromUser, toUser, subject, body);
	}
	
}
