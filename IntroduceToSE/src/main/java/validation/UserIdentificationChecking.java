package validation;

public interface UserIdentificationChecking extends UserDataValidationChecking{
	boolean isCorrectLength(String userId);
	boolean isCorrectCharacters(String userId);
	
}
