package repository;

public interface AccountRepository<T> {

	
	T findByUsernameId(String username_id);
	
	
	boolean isExist(String username_id);
	
	boolean isCorrect(String username_id, String password);
	
	void changePassword(T origin, String newPasswrod);
	
	void save(T origin);
	
	void remove(String username_id);
	
}
