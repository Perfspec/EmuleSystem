package com.infosys.emailapi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.infosys.emailapi.constants.Constant;
import com.infosys.emailapi.persistence.domain.User;
import com.infosys.emailapi.persistence.repository.UserRepository;

public class UserServiceTests {
	
	private UserService userService;
	
	private UserRepository mockRepo;
	
	private String testUsername;
	private String testPassword;
	
	private User inactiveUser;
	
	private User incorrectPasswordUser;
	private String testPassword2;

	private User activeUser;

	@Before
	public void setUp() throws Exception {
		mockRepo = mock(UserRepository.class);
		testUsername=Constant.SERVICE_TEST_USERNAME;
		testPassword=Constant.SERVICE_TEST_PASSWORD;
		testPassword2=Constant.SERVICE_TEST_PASSWORD2;
		inactiveUser= new User(testUsername,testPassword);
		inactiveUser.setActive(false);
		incorrectPasswordUser = new User(testUsername, testPassword2);
		activeUser = new User(testUsername,testPassword);
		
	}

	@Test
	public void login_IfUserDoesNotExistPrintThat() {
		when(mockRepo.findById(testUsername))
			.thenReturn(Optional.ofNullable(null));
		userService = new UserService(mockRepo);
		assertEquals(Constant.USER_DOES_NOT_EXIST,
				userService.login(testUsername, testPassword));
	}
	
	@Test
	public void login_IfUserExistsButInactivePrintThat() {
		when(mockRepo.findById(testUsername))
			.thenReturn(Optional.of(inactiveUser));
		userService = new UserService(mockRepo);
		assertEquals(Constant.USER_INACTIVE,
				userService.login(testUsername, testPassword));
	}
	
	@Test
	public void login_IfUserExistsIsActiveAndPasswordIncorrectPrintThat() {
		when(mockRepo.findById(testUsername))
			.thenReturn(Optional.of(incorrectPasswordUser));
		userService = new UserService(mockRepo);
		assertEquals(Constant.INCORRECT_PASSWORD,
				userService.login(testUsername, testPassword));
	}
	
	@Test
	public void login_IfUserExistsIsActiveAndPasswordCorrectPrintSuccess() {
		when(mockRepo.findById(testUsername))
			.thenReturn(Optional.of(activeUser));
		userService = new UserService(mockRepo);
		assertEquals(Constant.SUCESSFUL_LOGIN,
				userService.login(testUsername, testPassword));
	}
	
	@Test
	public void createUser_IfUsernameTakenPrintThat() {
		when(mockRepo.existsById(testUsername))
			.thenReturn(true);
		userService = new UserService(mockRepo);
		assertEquals(Constant.USERNAME_TAKEN,
				userService.createUser(testUsername, testPassword));
	}
	
	@Test
	public void createUser_IfUsernameNotTakenPrintUserCreated() {
		when(mockRepo.existsById(testUsername))
			.thenReturn(false);
		userService = new UserService(mockRepo);
		assertEquals(Constant.USER_CREATED,
				userService.createUser(testUsername, testPassword));
	}
}
