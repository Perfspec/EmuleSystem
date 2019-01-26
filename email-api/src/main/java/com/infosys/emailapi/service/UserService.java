package com.infosys.emailapi.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.emailapi.constants.Constant;
import com.infosys.emailapi.persistence.domain.User;
import com.infosys.emailapi.persistence.repository.UserRepository;

@Service
@Transactional(value=TxType.SUPPORTS)
public class UserService implements UserServicible {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserService() {}
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/* (non-Javadoc)
	 * @see com.infosys.emailapi.service.UserServicible#login(java.lang.String, java.lang.String)
	 */
	public String login(String username, String password) {
		Optional<User> userOpt = userRepository.findById(username);
		if(userOpt.isPresent()) {
			if(userOpt.get().isActive()) {
				User thisUser=userOpt.get();
				if(thisUser.getPassword().equals(password)) {
					return Constant.SUCESSFUL_LOGIN;
				} else {
					return Constant.INCORRECT_PASSWORD;
				}
			}
			else {
				return Constant.USER_INACTIVE;
			}
		} else {
			return Constant.USER_DOES_NOT_EXIST;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.infosys.emailapi.service.UserServicible#createUser(java.lang.String, java.lang.String)
	 */
	@Transactional(value=TxType.REQUIRED)
	public String createUser(String username, String password) {
		if(userRepository.existsById(username)) {
			return Constant.USERNAME_TAKEN;
		} else {
			User newUser = new User(username, password);
			userRepository.save(newUser);
			return Constant.USER_CREATED;
		}
	}
	
	

}
