package repository;

import java.util.List;

import model.Subject;
public interface SubjectRepository extends Repository<Subject> {

	List<Subject> findAll();

	Subject findById(String id);

	void save(Subject model);

	void remove(String id);

	Subject findByName(String name);
	
}
