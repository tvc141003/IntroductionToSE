package validation;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public implements UserUsernameCheckingImp extends UserUsernameChecking {
	
	
	public boolean isCorrectLength(String username) {
		if (username.length() < 0 || username.length() > 100) {
			return false;
			}
		return true;
	}
	
	
	public boolean isCorrectCharacters(String username) {
		byte[] newString = username.getBytes();
		CharsetDecoder decoder = 
				  StandardCharsets.UTF_16.newDecoder();
				try {
				  decoder.decode(
				       ByteBuffer.wrap(newString));		           
				} catch (CharacterCodingException ex) {		        
				      return false;
				} 
				return true;
	}
	
	
	public boolean isCorrectOwnCondition(String username) {
		int pos = username.contains(" ");
		if (pos == 0 || pos == -1 || pos == username.length()-1)
			return false;
		return false;
	};
}
