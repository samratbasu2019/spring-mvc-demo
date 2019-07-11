package com.sam.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sam.dao.UserDAO;
import com.sam.entity.UserRegistration;
import com.sam.model.UserDetail;

@Service
public class UserService implements IUserService {
	@Autowired
	UserDAO userDAO;

	@Override
	public boolean addUser(UserDetail userdetails) {
		boolean result=false;
		UserRegistration entity = new UserRegistration();
		  try {
	   	    	entity.setUsername(userdetails.getUsername());
	   	    	entity.setUserpassword(userdetails.getPassword());
	   	    	entity.setUseraddress(userdetails.getAddress());
	   	    	entity.setCity(userdetails.getCity());
	   	    	entity.setState(userdetails.getState());
	   	    	entity.setUseremail(userdetails.getEmail());
	   	    	
	   	    	result=userDAO.addUser(entity);
	         
	    	}
		  	catch (Exception e) {
	    		e.printStackTrace();
	    	}
	        return result;
		
	}
	
	@Override
	public List<UserDetail> getUser(String username,String password) {
		List<UserDetail> result = null;
		
		  try {
	   	    	
		//	System.out.println("Service Layer User Name "+username+" pasaword " + password);
	   	    	result= userDAO.getUser(username, password);
	         
	    	}
		  	catch (Exception e) {
	    		e.printStackTrace();
	    	}
	        return result;
		
	}

}
