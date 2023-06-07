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

import model.Manager;

import utils.HibernateUtils;


public class ManagerRepositoryImpl implements ManagerRepository {
	
	private static CriteriaQuery<Manager> cr ;
	private static Root<Manager> root;
	private static Session session;
	private static CriteriaBuilder cb; 
	static {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		session = factory.openSession();
		
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Manager.class);
		root  = cr.from(Manager.class);
		
	}
	
	public List<Manager> findAll() {
		
		cr.select(root) ;
		org.hibernate.query.Query<Manager> query = session.createQuery(cr);
		
		List<Manager> list  = query.getResultList();
		if (list.size() == 0 ) 
			return null;
		return list;
		
	}

	public Manager findById(String id) {
		cr.select(root).where(cb.equal(root.get("managerId"), id)) ;
		org.hibernate.query.Query<Manager> query = session.createQuery(cr);
		List<Manager> list  = query.getResultList();
		if (list.size() == 0 ) 
			return null;
		Manager manager = list.get(0);
		
		return manager;
	}

	public void save(Manager model) {
		session.save(model);
		
	}

	public void remove(String id) {
		CriteriaDelete<Manager> cd = cb.createCriteriaDelete(Manager.class);
		cd.where(cb.equal(root.get("managerId"), id));
		session.createQuery(cd).executeUpdate();
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Manager findByName(String name) {
		
		Predicate inFirstName = cb.like( (Expression)root.get("firstName"), "%" +name+"%");
		Predicate inLastName = cb.like( (Expression)root.get("lastName"), "%" +name+"%");
		cr.select(root).where(cb.or(inFirstName,inLastName));
		org.hibernate.query.Query<Manager> query = session.createQuery(cr);
		List<Manager> list  = query.getResultList();
		if (list.size() == 0 ) 
			return null;
		Manager manager = list.get(0);
		
		return manager;
	}
	
	
	
	private static  ManagerRepositoryImpl INSTANCE ;
	private ManagerRepositoryImpl() {
		
	}
	
	
	public static ManagerRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ManagerRepositoryImpl();
			
		}
		return INSTANCE;
	}

	
	

}
