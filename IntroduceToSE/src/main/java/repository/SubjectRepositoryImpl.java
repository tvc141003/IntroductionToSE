package repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import model.Subject;
import utils.HibernateUtils;


public class SubjectRepositoryImpl implements SubjectRepository {
	
	private static CriteriaQuery<Subject> cr ;
	private static Root<Subject> root;
	private static Session session;
	
	static {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		session = factory.openSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Subject.class);
		root  = cr.from(Subject.class);
		
	}
	
	public List<Subject> findAll() {
		
		cr.select(root) ;
		org.hibernate.query.Query<Subject> query = session.createQuery(cr);
		List<Subject> list  = query.getResultList();
		return list;
		
	}

	public Subject findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Subject model) {
		// TODO Auto-generated method stub
		
	}

	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	private static  SubjectRepositoryImpl INSTANCE ;
	private SubjectRepositoryImpl() {
		
	}
	
	
	public SubjectRepositoryImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SubjectRepositoryImpl();
			
		}
		return INSTANCE;
	}
	
	
	
	
	
	
	
	
	
	
	
}
