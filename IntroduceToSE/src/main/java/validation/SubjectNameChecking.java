package validation

public interface SubjectNameChecking extends SubjectDataValidationChecking{
	boolean isCorrectLength(String subjectName);
	boolean isCorrectCharacters(String subjectName);
}
