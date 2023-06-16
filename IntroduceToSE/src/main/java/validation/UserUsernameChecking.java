package validation;

public interface UserUsernameChecking extends UserDataValidationChecking {
	boolean isCorrectLength(String username);
	boolean isCorrectCharacters(String username);
	boolean isCorrectOwnCondition(String username);
}
