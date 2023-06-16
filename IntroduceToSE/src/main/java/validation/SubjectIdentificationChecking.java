package validation;

public interface SubjectIdentificationChecking extends SubjectDataValidationChecking{
	boolean isCorrectLength(String subectId);
	boolean isUpperCase(String subjectName);
}
