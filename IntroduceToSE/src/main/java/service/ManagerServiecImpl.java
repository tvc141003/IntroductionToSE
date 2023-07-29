package service;

import java.util.List;

import dataValidation.userValidation.emailChecking.GmailFormCheckingImpl;
import model.Manager;
import model.ManagerAccount;
import model.Subject;
import repository.AccountRepository;
import repository.ManagerAccountRepositoryImpl;
import utils.MailUtils;

public class ManagerServiecImpl implements ManagerService {
	private static  ManagerServiecImpl INSTANCE ;
	private GmailFormCheckingImpl mail;
	private ManagerServiecImpl() {
		
	}
	
	public static ManagerServiecImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ManagerServiecImpl();
		}
		return INSTANCE;
	}
	
	public boolean login(String id, String password)
	{
		if(ManagerAccountRepositoryImpl.getInstance().isCorrect(id, password) == true)
		{
			System.out.print("true Login");
			return true;
		}
		else
		{
			System.out.print("false Login");
			return false;
		}
		
	}

	@Override
	public Object viewMyProfile(Object origin) {
		Manager manager = (Manager) origin;
		//System.out.print(ManagerAccountRepositoryImpl.getInstance().findByUsernameId(manager.getManagerId()));
		ManagerAccount n = (ManagerAccount) ManagerAccountRepositoryImpl.getInstance().findByUsernameId(manager.getManagerId());
		System.out.print(n.getId() + " " + n.getPassword() + " " + n.getManager().getEmail());
		return n;
	}

	@Override
	public boolean forgotPassword(String id, String email) {
		mail = new GmailFormCheckingImpl();
		if(mail.check(email) == true)
		{
			String password =  "";
			password = ManagerAccountRepositoryImpl.getInstance().findByUsernameId(id).getPassword();
			
			MailUtils.getInstance().sendPassword(email, password);
			System.out.print(password);
			return true;
		}
		else {
			System.out.print("Email error");
			return false;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public boolean changePassword(Object origin, String password) {
		// TODO Auto-generated method stub
		ManagerAccount manager = (ManagerAccount) origin;
		ManagerAccountRepositoryImpl.getInstance().changePassword(manager, password);
		System.out.print(manager.getPassword());
		return true;
	}

	@Override
	public boolean joinSubject(Subject subject, Object origin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean outSubject(Subject subject, Object origin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Subject> viewSubject(Subject subject, Object origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Object origin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object origin) {
		// TODO Auto-generated method stub
		return false;
	}

}