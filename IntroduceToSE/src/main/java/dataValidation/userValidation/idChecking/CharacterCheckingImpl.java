package dataValidation.userValidation.idChecking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterCheckingImpl implements CharacterChecking{

	@Override
	public boolean check(String data) {
		// TODO Auto-generated method stub
		String regexPatternString = utils.UserRegrexConfig.CHAR_REGEX;
		Pattern pattern = Pattern.compile(regexPatternString);
		
		Matcher matcher = pattern.matcher(data);
		
		return matcher.matches();
	}

}
