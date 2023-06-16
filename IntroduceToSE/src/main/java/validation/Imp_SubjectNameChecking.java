package validation

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public implements SubjectNameCheckingImp extends SubjectNameChecking{
	
	public boolean isCorrectLength(String subjectName) {
		if (subjectName.length() < 0 || subjectName.length() > 100) {
			return false;
			}
		return true;
	}
	
	
	public boolean isCorrectCharacters(String subjectName) {
		byte[] newString = subjectName.getBytes();
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
}
