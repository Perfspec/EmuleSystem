package com.infosys.emailapi.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.infosys.emailapi.constants.Constant;

@Entity
@Table(name=Constant.EMAIL_TABLE_NAME)
public class Email {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=Constant.EMAIL_COLUMN_ID_NAME)
	private Long id;
	
	@OneToOne
	@JoinColumn(name=Constant.EMAIL_COLUMN_USER_FROM_NAME)
	private User userFrom;

	@OneToOne
	@JoinColumn(name=Constant.EMAIL_COLUMN_USER_TO_NAME)
	private User userTo;

	@Column(name=Constant.EMAIL_COLUMN_SUBJECT_NAME)
	private String subject;
	
	@Column(name=Constant.EMAIL_COLUMN_BODY_NAME)
	private String body;
	
	@Column(name=Constant.EMAIL_COLUMN_READ_NAME)
	private boolean read;
	
	@Column(name=Constant.EMAIL_COLUMN_DELETED_NAME)
	private boolean deleted;
	
	public Email() {}

	public Email(User userFrom, User userTo, String subject, String body) {
		this.userFrom = userFrom;
		this.userTo = userTo;
		this.subject = subject;
		this.body = body;
		this.read = Constant.EMAIL_CONSTRUCTOR_READ_DEFAULT;
		this.deleted = Constant.EMAIL_CONSTRUCTOR_DELETED_DEFAULT;
	}

	public Long getId() {
		return id;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public User getUserTo() {
		return userTo;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public boolean isRead() {
		return read;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
		
}
