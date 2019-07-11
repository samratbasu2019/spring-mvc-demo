package com.sam.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sam.entity.UserRegistration;
import com.sam.model.UserDetail;


@Repository
public class UserDAO{
	private final static Logger log = Logger.getLogger(UserDAO.class);
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public boolean addUser(UserRegistration userdetail){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(userdetail);	
		transaction.commit();
		session.close();
		return true;
	}
	

	public List<UserDetail> getUser(String username, String password){
		 Session session = sessionFactory.openSession();
		 Query query = null;
		 List<UserDetail> p = null;
		 log.debug("DAO Layer User id is " + username+" password : "+password);
		 query = session.getNamedQuery("getUserDetails");
		 query.setString("username", username);
		 query.setString("password", password);
		 Transaction transaction = session.beginTransaction();
		// System.out.println("User id is " + username+" password : "+password);
		 
		try {
		 p = query.list();
		}
		catch(Exception ex) {
		System.out.println("Exception: " +ex.getMessage());	
		}
		finally {
			 transaction.commit();
			 session.close();
		}
		 if (p.size()>0) 
			 return p;
		 
		 else {
			 p=null;
			 return p;
		 }
		
		 
		
	}
}