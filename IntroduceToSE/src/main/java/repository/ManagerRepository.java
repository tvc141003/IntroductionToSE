package repository;

import java.util.List;

import model.Manager;


public interface ManagerRepository extends Repository<Manager> {

	List<Manager> findAll();

	Manager findById(String id);
 
	void save(Manager model);

	void remove(String id);

	Manager findByName(String name);

	

}
