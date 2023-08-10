package repository;

import model.TeacherAccount;

public interface TeacherAccountRepository  extends AccountRepository<TeacherAccount>{

	boolean isExist(String username_id);

	boolean isCorrect(String username_id, String password);

	void changePassword(TeacherAccount origin, String newPasswrod);

	void save(TeacherAccount origin);

	void remove(String username_id);
	
	TeacherAccount findByUsernameId(String username_id);
}
