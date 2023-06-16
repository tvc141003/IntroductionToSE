package validation;

public interface AccountPasswordChecking extends AccountDataValidationChecking{
	boolean isCorrectLength(String password);
	boolean isCorrectCharacters(String password);
	boolean isCorrectIngredient(String password);
}
