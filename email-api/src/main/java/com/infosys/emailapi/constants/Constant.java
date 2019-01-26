package com.infosys.emailapi.constants;

public class Constant {
	
	public static final String USER_TABLE_NAME="users";

	public static final String USER_COLUMN_USERNAME_NAME = "username";

	public static final String USER_COLUMN_PASSWORD_NAME = "password";

	public static final String USER_COLUMN_ACTIVE_NAME = "active";
	
	public static final boolean USER_CONSTRUCTOR_ACTIVE_DEFAULT = true;

	public static final String EMAIL_TABLE_NAME="emails";
	
	public static final String EMAIL_COLUMN_ID_NAME = "id";

	public static final String EMAIL_COLUMN_USER_FROM_NAME = "userFrom";

	public static final String EMAIL_COLUMN_USER_TO_NAME = "userTo";

	public static final String EMAIL_COLUMN_SUBJECT_NAME = "subject";

	public static final String EMAIL_COLUMN_BODY_NAME = "body";

	public static final String EMAIL_COLUMN_READ_NAME = "seen";

	public static final String EMAIL_COLUMN_DELETED_NAME = "deleted";

	public static final boolean EMAIL_CONSTRUCTOR_READ_DEFAULT = false;

	public static final boolean EMAIL_CONSTRUCTOR_DELETED_DEFAULT = false;

	public static final String SUCESSFUL_LOGIN = "Sucessful Login";

	public static final String INCORRECT_PASSWORD = "Incorrect password for this username";

	public static final String USER_DOES_NOT_EXIST = "This user does not exist. Please check for spelling or create new user.";

	public static final String USER_INACTIVE = "This username exists but the associated user is inactive. Please use another username.";
	
	public static final String USERNAME_TAKEN = "This username is already taken. Please try another.";

	public static final String USER_CREATED = "User sucessfully created";

	public static final String SERVICE_TEST_USERNAME = "KwanHoChan94";
	
	public static final String SERVICE_TEST_PASSWORD = "boggled33b00p";

	public static final String SERVICE_TEST_PASSWORD2 = "hjdkajha";

	public static final String EMAIL_QUERY_GET_BY_USERNAME = "SELECT e FROM Email e WHERE e."+EMAIL_COLUMN_USER_TO_NAME
			+"=?1 AND e."+EMAIL_COLUMN_DELETED_NAME+"=false";

	public static final String EMAIL_DOES_NOT_EXIST = "This email does not exist.";

	public static final String EMAIL_ALREADY_DELETED = "This email had already been deleted.";

	public static final String EMAIL_DELETION_SUCCESSFUL = "This email was successfully deleted";

	public static final String EMAIL_IS_MISSING_FIELDS = "This email is missing fields. None of them can be null.";

	public static final String EMAIL_COMPOSED_SUCCESSFULLY = "Email composed succesfully";

	public static final String USER_PATH = "/user";
	
	public static final String EMAIL_PATH = "/email";

	public static final String USER_LOGIN_PATH = "/"+USER_COLUMN_USERNAME_NAME+"={"+USER_COLUMN_USERNAME_NAME
			+"}&"+USER_COLUMN_PASSWORD_NAME+"={"+USER_COLUMN_PASSWORD_NAME+"}";
	
	public static final String USER_CREATE_PATH = "/create";

}
