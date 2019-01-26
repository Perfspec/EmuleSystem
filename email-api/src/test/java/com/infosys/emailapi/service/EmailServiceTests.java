package com.infosys.emailapi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.infosys.emailapi.constants.Constant;
import com.infosys.emailapi.persistence.domain.Email;
import com.infosys.emailapi.persistence.domain.User;
import com.infosys.emailapi.persistence.repository.EmailRepository;

public class EmailServiceTests {

	private EmailServicible emailService;
	private EmailRepository mockRepo;

	private List<Email> emailsForTestUser;
	private String testUsername1;
	private String testPassword1;
	private String testUsername2;
	private String testPassword2;
	private int emailsForTestUserLength;
	private Long noEmailHere;
	private Long anEmailHere;
	private User testUser1;
	private User testUser2;
	private String subject;
	private String body;
	private Email deletedEmail;
	private Email undeletedEmail;

	@Before
	public void setUp() throws Exception {
		mockRepo = mock(EmailRepository.class);
		emailsForTestUser = new ArrayList<Email>();
		emailsForTestUserLength = emailsForTestUser.size();
		noEmailHere = 1L;
		anEmailHere = 1L;
		testUsername1 = "AwfulGun75";
		testPassword1 = "BringOnTheDogs";
		testUsername2 = "BoneMonkey10";
		testPassword2 = "BringOnTheDogs";
		testUser1 = new User(testUsername1, testPassword1);
		testUser2 = new User(testUsername2, testPassword2);
		subject = "Carpenter!";
		body = "it's a risk";
		deletedEmail = new Email(testUser1, testUser2, subject, body);
		deletedEmail.setDeleted(true);
		undeletedEmail = new Email(testUser1, testUser2, subject, body);
	}

	@Test
	public void getByUsername_returnAllUndeletedEmailsToThatUser() {
		when(mockRepo.getByUsername(testUsername1)).thenReturn(emailsForTestUser);
		emailService = new EmailService(mockRepo);
		assertEquals(emailsForTestUserLength, emailService.getByUsername(testUsername1).size());
	}

	@Test
	public void deleteEmail_ifEmailDoesNotExistPrintThat() {
		when(mockRepo.findById(noEmailHere))
		.thenReturn(Optional.ofNullable(null));
		emailService = new EmailService(mockRepo);
		assertEquals(Constant.EMAIL_DOES_NOT_EXIST,
				emailService.deleteEmail(noEmailHere));
	}

	@Test
	public void deleteEmail_ifEmailDoesExistAndIsDeletedPrintThat() {
		when(mockRepo.findById(anEmailHere))
			.thenReturn(Optional.of(deletedEmail));
		emailService = new EmailService(mockRepo);
		assertEquals(Constant.EMAIL_ALREADY_DELETED,
				emailService.deleteEmail(anEmailHere));
	}
	
	@Test
	public void deleteEmail_ifEmailDoesExistAndIsNotDeletedPrintSuccess() {
		when(mockRepo.findById(anEmailHere))
			.thenReturn(Optional.of(undeletedEmail));
		emailService = new EmailService(mockRepo);
		assertEquals(Constant.EMAIL_DELETION_SUCCESSFUL,
				emailService.deleteEmail(anEmailHere));
	}
	
	@Test
	public void composeEmail_ifEmailIsMissingFromUserPrintThat() {
		emailService = new EmailService(mockRepo);
		testUser1=null;
		assertEquals(Constant.EMAIL_IS_MISSING_FIELDS, emailService
				.composeEmail(testUser1, testUser2, subject, body));
	}
	
	@Test
	public void composeEmail_ifEmailIsMissingToUserPrintThat() {
		emailService = new EmailService(mockRepo);
		testUser2=null;
		assertEquals(Constant.EMAIL_IS_MISSING_FIELDS, emailService
				.composeEmail(testUser1, testUser2, subject, body));
	}
	
	@Test
	public void composeEmail_ifEmailIsMissingSubjectPrintThat() {
		emailService = new EmailService(mockRepo);
		subject=null;
		assertEquals(Constant.EMAIL_IS_MISSING_FIELDS, emailService
				.composeEmail(testUser1, testUser2, subject, body));
	}
	
	@Test
	public void composeEmail_ifEmailIsMissingBodyPrintThat() {
		emailService = new EmailService(mockRepo);
		body=null;
		assertEquals(Constant.EMAIL_IS_MISSING_FIELDS, emailService
				.composeEmail(testUser1, testUser2, subject, body));
	}
	
	@Test
	public void composeEmail_ifAllFieldsPresentPrintSuccess() {
		emailService = new EmailService(mockRepo);
		assertEquals(Constant.EMAIL_COMPOSED_SUCCESSFULLY, emailService
				.composeEmail(testUser1, testUser2, subject, body));
	}
}
