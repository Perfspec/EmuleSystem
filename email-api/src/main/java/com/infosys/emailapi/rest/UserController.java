package com.infosys.emailapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.emailapi.constants.Constant;
import com.infosys.emailapi.service.UserServicible;

@RestController
@RequestMapping(path = Constant.USER_PATH)
public class UserController {
	
	@Autowired
	UserServicible userService;

	@GetMapping(path=Constant.USER_LOGIN_PATH)
	public String login(
			@PathVariable(name=Constant.USER_COLUMN_USERNAME_NAME) String username,
			@PathVariable(name=Constant.USER_COLUMN_PASSWORD_NAME) String password) {
		return userService.login(username, password);
	}
	
	@PostMapping(path=Constant.USER_CREATE_PATH)
	public String createUser(
			@RequestParam(name=Constant.USER_COLUMN_USERNAME_NAME) String username,
			@RequestParam(name=Constant.USER_COLUMN_PASSWORD_NAME) String password) {
		return userService.createUser(username, password);
	}
	
}
