package repository;

import java.util.List;

import model.Teacher;

public interface TeacherRepository extends Repository<Teacher> {

	List<Teacher> findAll();

	Teacher findById(String id);

	void save(Teacher model);

	void remove(String id);

	Teacher findByName(String name);
	
}
