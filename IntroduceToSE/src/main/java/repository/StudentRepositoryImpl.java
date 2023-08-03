package repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import model.Student;
import utils.HibernateUtils;


public class StudentRepositoryImpl implements StudentRepository {
	
	
	private static Root<Student> root;
	private static Session session;
	private static CriteriaBuilder cb;
	private static SessionFactory factory = HibernateUtils.getSessionFactory();
	
	
	public List<Student> findAll() {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		
		CriteriaQuery<Student> cr = cb.createQuery(Student.class);
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
		CriteriaQuery<Student> cr = cb.createQuery(Student.class);
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
		
		Transaction tx = session.beginTransaction();
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close(); // close session
		}
		
	}
		


	public void remove(String id) {
		Student student = this.findById(id);
		if (student == null) return ;
		
		session = factory.openSession(); //open session
		
		session.delete(student);
		
		
		Transaction tx = session.beginTransaction();
		
		
		
		
	
		
		try {
			tx.commit();
		} 
		catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close(); // close session
		}
		
	}
		
	
	
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Student findByName(String name) {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> cr = cb.createQuery(Student.class);
		root  = cr.from(Student.class);
		
		Predicate inFirstName = cb.like( (Expression)root.get("firstName"), "%" +name+"%");
		Predicate inLastName = cb.like( (Expression)root.get("lastName"), "%" +name+"%");
		
		
		cr.select(root).where(cb.or(inFirstName,inLastName));
		org.hibernate.query.Query<Student> query = session.createQuery(cr);
		List<Student> list  = query.getResultList();
		session.close();
		if(list.size() == 0 )
			return null;	
		Student student = list.get(0);
	
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
