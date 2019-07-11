package com.sam.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sam.model.UserDetail;
import com.sam.service.IUserService;



@RestController
public class UserDetails { 
	@Autowired
	IUserService service;
	
		//http://localhost:8080/springangularDemo/addUser
		@RequestMapping(value="/addUser",method = RequestMethod.POST,
				consumes= MediaType.APPLICATION_JSON_VALUE,
				produces= MediaType.TEXT_HTML_VALUE
				)
		public void addUser(@RequestBody UserDetail userdetails) {
		    boolean result=false;
		    try {
		    System.out.println("Welcome to the request handler " + userdetails.getUsername());
		   result=service.addUser(userdetails);
		    if(result==true)
		    		System.out.println("User registration successful");
		    else
		    	System.out.println("Registration failed");
		    }
		    catch (Exception ex) {
		    	ex.printStackTrace();
		    }
		    
		}
		
		//http://localhost:8080/springangularDemo/getUsers?username=samtina.basu@gmail.com&password=samrat123
	    @RequestMapping(value="/getUsers",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody List<UserDetail> getAllUserDetails(@RequestParam("username") String username, @RequestParam("password") String password){
	    	List<UserDetail> userList = null; 
	    	
	    	try {
		    	userList=service.getUser(username,password);
		    } catch (Exception e) {
					e.printStackTrace();
			}
	    	//System.out.println("Log "+ userList);
		    return userList;
	    }
}