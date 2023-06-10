package repository;

public interface AccountRepository<T> {

	boolean isExist(String username_id);
	
	boolean isCorrect(String username_id, String password);
	
	void changePassword(T origin, String newPasswrod);
	
	void save(T origin);
	
	void remove(T origin);
	
}
