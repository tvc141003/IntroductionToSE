package validation;

public implements UserIdentificationCheckingImp extends UserIdentificationChecking{
	public boolean isCorrectLength(String userId) {
		if (userId.length() < 5)
			return false;
		else return true;
	};
	public boolean isCorrectCharacters(String userId) {
		char startChar = userId.charAt(0);
		if (startChar != 'M' && startChar != 'S' && startChar != 'T')
			return false;
		return true;
	};
}
