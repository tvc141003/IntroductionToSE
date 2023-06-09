package repository;

import model.StudentAccount;

public interface StudentAccountRepository extends AccountRepository<StudentAccount>{

	boolean isExist(String username_id);

	boolean isCorrect(String username_id, String password);

	void changePassword(StudentAccount origin, String newPasswrod);

	void save(StudentAccount origin);

	void remove(StudentAccount origin);
	
}
