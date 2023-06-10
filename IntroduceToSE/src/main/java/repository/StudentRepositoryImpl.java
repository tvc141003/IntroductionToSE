package repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import model.Student;
import utils.HibernateUtils;


public class StudentRepositoryImpl implements StudentRepository {
	
	private static CriteriaQuery<Student> cr ;
	private static Root<Student> root;
	private static Session session;
	private static CriteriaBuilder cb;
	private static SessionFactory factory = HibernateUtils.getSessionFactory();
	
	
	public List<Student> findAll() {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Student.class);
		root  = cr.from(Student.class);
		cr.select(root) ;
		org.hibernate.query.Query<Student> query = session.createQuery(cr);
		List<Student> list  = query.getResultList();
		if (list.size() == 0 ) 
		{
			session.close();
			return null;
		}
		session.close();
		return list;
		
		
	}

	public Student findById(String id) {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Student.class);
		root  = cr.from(Student.class);
		
		cr.select(root).where(cb.equal(root.get("studentId"), id));
		
		org.hibernate.query.Query<Student> query = session.createQuery(cr);
		List<Student> list  = query.getResultList();
		if (list.size() == 0 ) 
		{
			session.close();
			return null;
		}
		Student student = list.get(0);
		session.close();
		return student ;
	}

	public void save(Student model) {
		session = factory.openSession();
	
		session.save(model);
		session.close();
		
	}

	public void remove(String id) {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		root  = cr.from(Student.class);
		
		CriteriaDelete<Student> cd = cb.createCriteriaDelete(Student.class);
		cd.where(cb.equal(root.get("studentId"), id));
		session.createQuery(cd).executeUpdate();
		session.close();
		
	}
	
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Student findByName(String name) {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		root  = cr.from(Student.class);
		
		Predicate inFirstName = cb.like( (Expression)root.get("firstName"), "%" +name+"%");
		Predicate inLastName = cb.like( (Expression)root.get("lastName"), "%" +name+"%");
		
		
		cr.select(root).where(cb.or(inFirstName,inLastName));
		org.hibernate.query.Query<Student> query = session.createQuery(cr);
		List<Student> list  = query.getResultList();
		if(list.size() == 0 )
			{session.close();
			return null;
			}
		Student student = list.get(0);
		session.close();
		return student ;
		
	}
	
	private static  StudentRepositoryImpl INSTANCE ;
	private StudentRepositoryImpl() {
		
	}
	
	
	

	public static StudentRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new StudentRepositoryImpl();
			
		}
		return INSTANCE;
	}
	
	
	
	
	
}
