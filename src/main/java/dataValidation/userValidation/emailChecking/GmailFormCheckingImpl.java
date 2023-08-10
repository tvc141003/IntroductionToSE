package dataValidation.userValidation.emailChecking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailFormCheckingImpl implements GmailFormChecking{

	@Override
	public boolean check(String data) {
		String regexPatternString = utils.EmailRegexConfig.EMAIL_REGREX;
		Pattern pattern = Pattern.compile(regexPatternString);
		
		Matcher matcher = pattern.matcher(data);
		
		return matcher.matches();
	}


	
}
