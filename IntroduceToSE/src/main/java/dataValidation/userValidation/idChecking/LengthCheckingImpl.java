package dataValidation.userValidation.idChecking;

public class LengthCheckingImpl  implements LengthChecking{

	@Override
	public boolean check(String data) {
		return (data.length() == 5);
	}

}
