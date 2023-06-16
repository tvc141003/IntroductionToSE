package validation;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public implements AccountPasswordCheckingImp extends AccountPasswordChecking{
	
	@override
	
	public boolean isCorrectLength(String password){
		if (password.length() < 8 || password.length() > 24) {
			return false;
			}
		return true;
	};
	
	
	public boolean isCorrectCharacters(String password){
		byte[] newString = password.getBytes();
		CharsetDecoder decoder = 
				  StandardCharsets.UTF_8.newDecoder();
				try {
				  decoder.decode(
				       ByteBuffer.wrap(newString));		           
				} catch (CharacterCodingException ex) {		        
				      return false;
				} 
				return true;
	};
	
	
	public boolean isCorrectIngredient(String password){
		int lw = 0;
		int up = 0;
		int nb = 0;
		int spc = 0;
		
		for (int i = 0 ; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isLowerCase(c))
				lw++;
			else if (Character.isUpperCase(c))
				up++;
			else if (Character.isDigit(c))
				nb++;
			else if (!Character.isWhitespace(c))
				spc++;
		}
		if (lw*up*nb*spc == 0)
			return false;
		return true;
	};
}