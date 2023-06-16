package validation

public implements SubjectCreditCheckingImp extends SubjectCreditChecking {
	public boolean isCorrectCredit(int credit) {
		if (creadit < 2 || credit >10)
			return false;
		return true;
	}
}
