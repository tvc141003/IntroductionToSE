package repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Manager;
import model.Student;
import utils.HibernateUtils;


public class ManagerRepositoryImpl implements ManagerRepository {
	
	private static CriteriaQuery<Manager> cr ;
	private static Root<Manager> root;
	private static Session session;
	
	static {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		session = factory.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Manager.class);
		root  = cr.from(Manager.class);
		
	}
	
	public List<Manager> findAll() {
		
		cr.select(root) ;
		org.hibernate.query.Query<Manager> query = session.createQuery(cr);
		List<Manager> list  = query.getResultList();
		return list;
		
	}

	public Manager findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Manager model) {
		// TODO Auto-generated method stub
		
	}

	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	private static  ManagerRepositoryImpl INSTANCE ;
	private ManagerRepositoryImpl() {
		
	}
	
	
	public ManagerRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ManagerRepositoryImpl();
			
		}
		return INSTANCE;
	}
	
	
	
	
	
	
	
	
	
	
	
}
