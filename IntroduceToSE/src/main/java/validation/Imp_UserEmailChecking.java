package validation;

public implements UserEmailCheckingImp extends UserEmailChecking{
	
	public boolean isCorrectGmailForm(String email) {
	int length = email.length();
		
		if (email.charAt(0) == '.' || email.contains("..") || !email.contains("@"))
			return false;
		
		int posAtsign = email.indexOf("@");
		String username = email.substring(0, posAtsign);
		String domain = email.substring(posAtsign+1, length);
		
		//user name 
		int usernameLength = username.length();
		if (usernameLength == 0)
			return false;
		for (int i = 0 ; i < usernameLength ; i++) {
			char c = username.charAt(i);
			if (!Character.isDigit(c) && !Character.isLetter(c) && c!='.')
				return false;
		}
		
		//domain
		int domainLength = domain.length();
		if (domain.indexOf("gmail.") != 0)
			
			return false;
		for (int i = 0 ; i < domainLength ; i++) {
			char c = domain.charAt(i);
			if (!Character.isDigit(c) && !Character.isLetter(c) && c!='.')
				return false;
		}
		return true;
	};
}
