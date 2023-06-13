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

import model.Manager;

import utils.HibernateUtils;

public class ManagerRepositoryImpl implements ManagerRepository {

	
	private static Root<Manager> root;
	private static Session session;
	private static CriteriaBuilder cb;
	private static SessionFactory factory = HibernateUtils.getSessionFactory();
	

	
	  public List<Manager> findAll() {
		  session = factory.openSession(); // open session
		  cb = session.getCriteriaBuilder();
		  CriteriaQuery<Manager> cr = cb.createQuery(Manager.class);
		  root = cr.from(Manager.class);
		  cr.select(root) ; 
		  org.hibernate.query.Query<Manager> query = session.createQuery(cr);
		  List<Manager> list = query.getResultList();
		  session.close(); /// close session
		  if (list.size() == 0 ) return null; 
		  return list;
	  }
	public Manager findById(String id) {
		session = factory.openSession(); // open session
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Manager> cr = cb.createQuery(Manager.class);
		root = cr.from(Manager.class);
		 
		cr.select(root).where(cb.equal(root.get("managerId"), id));
		org.hibernate.query.Query<Manager> query = session.createQuery(cr);
		List<Manager> list = query.getResultList();
		session.close(); // close session
		if (list.size() == 0)
			return null;
		
		Manager manager = list.get(0);

		return manager;
	}

	public void save(Manager model) {
		session = factory.openSession(); // open session
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
		
		
		Manager manager = this.findById(id);
		if (manager == null) return ;
		
		session = factory.openSession(); //open session
		
		session.delete(manager);
		
		
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
	public Manager findByName(String name) {
		session = factory.openSession(); // open session
		cb = session.getCriteriaBuilder();
		CriteriaQuery<Manager> cr = cb.createQuery(Manager.class);
		root = cr.from(Manager.class);
		Predicate inFirstName = cb.like((Expression) root.get("firstName"), "%" + name + "%");
		Predicate inLastName = cb.like((Expression) root.get("lastName"), "%" + name + "%");
		cr.select(root).where(cb.or(inFirstName, inLastName));
		org.hibernate.query.Query<Manager> query = session.createQuery(cr);
		List<Manager> list = query.getResultList();
		if (list.size() == 0)
			return null;
		Manager manager = list.get(0);

		return manager;
	}

	private static ManagerRepositoryImpl INSTANCE;

	private ManagerRepositoryImpl() {

	}

	public static ManagerRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ManagerRepositoryImpl();

		}
		return INSTANCE;
	}

	

}
