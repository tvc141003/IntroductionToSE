package validation;

public implements UserGenderCheckingImp extends UserGenderChecking {
	public boolean isCorrectGender(String gender) {
		if (gd != "male" && gd != "female")
			return false;
		return true;
	};
}
