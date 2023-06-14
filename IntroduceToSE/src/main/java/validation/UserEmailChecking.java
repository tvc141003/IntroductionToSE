package validation;

public interface UserEmailChecking extends UserDataValidationChecking {
	boolean isCorrectGmailForm(String email);

}
