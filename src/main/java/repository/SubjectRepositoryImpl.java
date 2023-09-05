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


import model.Subject;
import utils.HibernateUtils;


public class SubjectRepositoryImpl implements SubjectRepository {
	

	private static Root<Subject> root;
	private static Session session;
	private static CriteriaBuilder cb;
	private static SessionFactory factory = HibernateUtils.getSessionFactory();
	
	
	public List<Subject> findAll() {
		session = factory.openSession() ;
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Subject> cr = cb.createQuery(Subject.class);
		root = cr.from(Subject.class);
		cr.select(root) ;
		org.hibernate.query.Query<Subject> query = session.createQuery(cr);
		List<Subject> list  = query.getResultList();
		
		session.close();
		if (list.size() == 0 ) 
			return null;
		return list;
		
	}

	public Subject findById(String id) {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Subject> cr = cb.createQuery(Subject.class);
		root = cr.from(Subject.class);
		cr.select(root).where(cb.equal(root.get("subjectId"), id));
		org.hibernate.query.Query<Subject> query = session.createQuery(cr);
		List<Subject> list  = query.getResultList();
		
		session.close();
		if (list.size() == 0 ) 
			return null;
		
		Subject subject = list.get(0);
		
		return subject;
	}

	public void save(Subject model) {
		session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(model);
	
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
		Subject subject = this.findById(id);
		if (subject == null) return ;
		
		session = factory.openSession(); //open session	
		
		session.delete(subject);
		
		
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

	
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Subject findByName(String name) {
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Subject> cr = cb.createQuery(Subject.class);
		root  = cr.from(Subject.class);
		
		Predicate predicate = cb.like((Expression)root.get("name"), "%"+name+"%");
		cr.select(root).where(predicate);
		
		org.hibernate.query.Query<Subject> query = session.createQuery(cr);
		List<Subject> list  = query.getResultList();
		if (list.size() == 0 ) 
			return null;
		Subject subject = list.get(0);
		
		return subject;
		
	}






	private static  SubjectRepositoryImpl INSTANCE ;
	private SubjectRepositoryImpl() {
		
	}
	
	
	public static SubjectRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SubjectRepositoryImpl();
			
		}
		return INSTANCE;
	}
	
	
	
	
	
	
	
	
	
	
	
}
