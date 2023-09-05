package repository;

import java.util.List;

import model.Teacher;
import model.Teacher_Subject;

public interface TeacherRepository extends Repository<Teacher> {

	List<Teacher> findAll();

	Teacher findById(String id);

	void save(Teacher model);

	void remove(String id);

	Teacher findByName(String name);
	
	void removeTeacher_Subject(Teacher t ,Teacher_Subject ts);
	
}
