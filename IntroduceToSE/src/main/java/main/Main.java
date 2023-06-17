package main;




import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mysql.cj.xdevapi.Schema.Validation;

import dataValidation.userValidation.emailChecking.GmailFormCheckingImpl;
import dataValidation.userValidation.nameChecking.CharacterCheckingImpl;
import model.Manager;
import model.ManagerAccount;
import model.Student;
import model.StudentAccount;
import repository.AccountRepository;
import repository.ManagerAccountRepository;
import repository.ManagerAccountRepositoryImpl;
import repository.ManagerRepositoryImpl;
import repository.StudentAccountRepositoryImpl;

import repository.StudentRepositoryImpl;
import utils.HibernateUtils;





public class Main {

	public static void main(String args[]) {
	
		 SessionFactory factory = HibernateUtils.buildSessionFactory(); Session
		 session = factory.openSession();
		 
		 Transaction tx = session.beginTransaction();
		 
		 Manager manager = new Manager("M001", "Le", "Trong", true, "lehuutrong141@gmail.com"); 
		 ManagerRepositoryImpl managerRepository =
		 ManagerRepositoryImpl.getInstance();
		 
		 managerRepository.save(manager);
		
		 //managerAccountRepository.remove(managerAccount.getManager().getManagerId());
		 //managerRepository.remove("M001");
		 
		 
		 //----------------//
		 
		 try { 
			 tx.commit(); 
		 } catch (HibernateException e) { 
			 tx.rollback();
			 e.printStackTrace();
		 } 
		 session.close();
		 
		
	}
}
