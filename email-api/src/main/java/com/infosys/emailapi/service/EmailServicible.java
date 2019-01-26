package com.infosys.emailapi.service;

import java.util.List;

import com.infosys.emailapi.persistence.domain.Email;
import com.infosys.emailapi.persistence.domain.User;

public interface EmailServicible {

	List<Email> getByUsername(String username);

	String deleteEmail(Long id);

	String composeEmail(User fromUser, User toUser, String subject, String body);

}