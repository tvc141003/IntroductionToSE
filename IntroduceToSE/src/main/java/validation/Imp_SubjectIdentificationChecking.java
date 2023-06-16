package validation

public implements SubjectIdentificationCheckingImp extends SubjectIdentificationChecking{
	public boolean isCorrectLength(String subectId) {
		if (id.length() != 8)
			return false;
		else 
			return true;
	}
	
	
	public boolean isUpperCase(String subjectName) {
		String letter = id.substring(0, 2);
		String digit = id.substring(3, 7);
		
		for (int i = 0 ; i < 3 ; i ++) {
			char c = letter.charAt(i);
			if (!Character.isLetter(c) || !Character.isUpperCase(c))
				return false;
		}
		
		for (int i = 0 ; i < 5; i++) {
			char c = digit.charAt(i);
			if (!Character.isDigit(c) )
				return false;
		}
		
		return true;
	}
}
