package repository;

import java.util.List;

import model.Student;

public interface StudentRepository extends Repository<Student> {

	List<Student> findAll();

	Student findById(String id);

	void save(Student model);

	void remove(String id);

	Student findByName(String name);
	
}
