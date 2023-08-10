package repository;

import model.ManagerAccount;

public interface ManagerAccountRepository extends AccountRepository<ManagerAccount> {

	boolean isExist(String username_id);

	boolean isCorrect(String username_id, String password);

	void changePassword(ManagerAccount origin, String newPasswrod);

	void save(ManagerAccount origin);

	ManagerAccount findByUsernameId(String username_id) ;

	void remove(String username_id) ;
	
}

