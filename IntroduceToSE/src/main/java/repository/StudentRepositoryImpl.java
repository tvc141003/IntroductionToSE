package repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import model.Student;
import utils.HibernateUtils;


public class StudentRepositoryImpl implements StudentRepository {
	
	private static CriteriaQuery<Student> cr ;
	private static Root<Student> root;
	private static Session session;
	
	static {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		session = factory.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Student.class);
		root  = cr.from(Student.class);
		
	}
	
	public List<Student> findAll() {
		
		cr.select(root) ;
		org.hibernate.query.Query<Student> query = session.createQuery(cr);
		List<Student> list  = query.getResultList();
		return list;
		
	}

	public Student findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Student model) {
		// TODO Auto-generated method stub
		
	}

	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	private static  StudentRepositoryImpl INSTANCE ;
	private StudentRepositoryImpl() {
		
	}
	
	
	public StudentRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new StudentRepositoryImpl();
			
		}
		return INSTANCE;
	}
	
	
	
	
	
	
	
	
	
	
	
}
