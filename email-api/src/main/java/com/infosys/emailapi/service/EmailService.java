package com.infosys.emailapi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.emailapi.constants.Constant;
import com.infosys.emailapi.persistence.domain.Email;
import com.infosys.emailapi.persistence.domain.User;
import com.infosys.emailapi.persistence.repository.EmailRepository;

@Service
@Transactional(value=TxType.SUPPORTS)
public class EmailService implements EmailServicible {
	
	@Autowired
	private EmailRepository emailRepository;

	public EmailService() {};
	
	public EmailService(EmailRepository emailRepository) {
		this.emailRepository=emailRepository;
	}

	/* (non-Javadoc)
	 * @see com.infosys.emailapi.service.EmailServicible#getByUsername(java.lang.String)
	 */
	public List<Email> getByUsername(String username) {
		return emailRepository.getByUsername(username);
	}
	
	/* (non-Javadoc)
	 * @see com.infosys.emailapi.service.EmailServicible#deleteEmail(java.lang.Long)
	 */
	@Transactional(value=TxType.REQUIRED)
	public String deleteEmail(Long id) {
		Optional<Email> optEmail = emailRepository.findById(id);
		if(optEmail.isPresent()) {
			Email thisEmail = optEmail.get();
			if(thisEmail.isDeleted()) {
				return Constant.EMAIL_ALREADY_DELETED;
			}
			else {
				thisEmail.setDeleted(true);
				emailRepository.save(thisEmail);
				return Constant.EMAIL_DELETION_SUCCESSFUL;
			}
		} else {
			return Constant.EMAIL_DOES_NOT_EXIST;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.infosys.emailapi.service.EmailServicible#composeEmail(com.infosys.emailapi.persistence.domain.User, com.infosys.emailapi.persistence.domain.User, java.lang.String, java.lang.String)
	 */
	@Transactional(value=TxType.REQUIRED)
	public String composeEmail(User fromUser, User toUser, String subject, String body) {
		if(anyNull(fromUser, toUser, subject, body)) {
			return Constant.EMAIL_IS_MISSING_FIELDS;
		} else {
			Email newEmail = new Email(fromUser, toUser, subject, body);
			emailRepository.save(newEmail);
			return Constant.EMAIL_COMPOSED_SUCCESSFULLY;
		}
	}

	private boolean anyNull(User fromUser, User toUser, String subject, String body) {
		return fromUser==null || toUser==null || subject==null || body==null;
	}
}
