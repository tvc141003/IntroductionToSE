package repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import model.Student;
import model.Teacher;
import utils.HibernateUtils;


public class TeacherRepositoryImpl implements TeacherRepository {
	
	private static CriteriaQuery<Teacher> cr ;
	private static Root<Teacher> root;
	private static Session session;
	
	static {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		session = factory.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Teacher.class);
		root  = cr.from(Teacher.class);
		
	}
	
	public List<Teacher> findAll() {
		
		cr.select(root) ;
		org.hibernate.query.Query<Teacher> query = session.createQuery(cr);
		List<Teacher> list  = query.getResultList();
		return list;
		
	}

	public Teacher findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save( Teacher model) {
		// TODO Auto-generated method stub
		
	}

	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	private static  TeacherRepositoryImpl INSTANCE ;
	private TeacherRepositoryImpl() {
		
	}
	
	
	public TeacherRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TeacherRepositoryImpl();
			
		}
		return INSTANCE;
	}
	
	
	
	
	
	
	
	
	
	
	
}
