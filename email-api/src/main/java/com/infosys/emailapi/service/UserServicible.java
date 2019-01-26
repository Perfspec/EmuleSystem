package com.infosys.emailapi.service;

public interface UserServicible {

	String login(String username, String password);

	String createUser(String username, String password);

}