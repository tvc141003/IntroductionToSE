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
import model.Subject;
import utils.HibernateUtils;


public class SubjectRepositoryImpl implements SubjectRepository {
	
	private static CriteriaQuery<Subject> cr ;
	private static Root<Subject> root;
	private static Session session;
	private static CriteriaBuilder cb;
	
	static {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		session = factory.openSession();
		cb = session.getCriteriaBuilder();
		cr = cb.createQuery(Subject.class);
		root  = cr.from(Subject.class);
		
	}
	
	public List<Subject> findAll() {
		
		cr.select(root) ;
		org.hibernate.query.Query<Subject> query = session.createQuery(cr);
		List<Subject> list  = query.getResultList();
		if (list.size() == 0 ) 
			return null;
		return list;
		
	}

	public Subject findById(String id) {
		cr.select(root).where(cb.equal(root.get("subjectId"), id));
		org.hibernate.query.Query<Subject> query = session.createQuery(cr);
		List<Subject> list  = query.getResultList();
		if (list.size() == 0 ) 
			return null;
		Subject subject = list.get(0);
		
		return subject;
	}

	public void save(Subject model) {
		session.save(model);
	}

	public void remove(String id) {
		CriteriaDelete<Subject> cd = cb.createCriteriaDelete(Subject.class);
		cd.where(cb.equal(root.get("subjectId"),root));
		session.createQuery(cd).executeUpdate();
	}

	
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Subject findByName(String name) {
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
