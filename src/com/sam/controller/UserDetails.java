package com.sam.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sam.model.UserDetail;
import com.sam.response.Response;
import com.sam.service.IUserService;

@RestController
public class UserDetails {
	private final static Logger log = Logger.getLogger(UserDetails.class);
	@Autowired
	IUserService service;
	Response response = new Response();

	// http://localhost:8080/springangularDemo/addUser
	@RequestMapping(value = "/addUser", method = RequestMethod.POST,
			/* consumes= MediaType.APPLICATION_JSON_VALUE, */
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Object addUser(@RequestBody UserDetail userdetails) {

		boolean result = false;
		try {
			log.debug("Welcome to the request handler " + userdetails.getUsername());
			result = service.addUser(userdetails);
			if (result == true) {
				log.debug("User registration successful");
				response.setStatus("success");
				response.setErrorCode(200);
				response.setMessage("Data saved successfully");
			} else {
				log.debug("Registration failed");
				response.setStatus("failed");
				response.setErrorCode(402);
				response.setMessage("Error processing the request...");
			}
		} catch (Exception ex) {
			response.setStatus("failed");
			response.setErrorCode(402);
			response.setMessage(ex.getCause().toString());
			ex.printStackTrace();
		}
		return response;
	}

	// http://localhost:8080/springangularDemo/getUsers?username=samtina.basu@gmail.com&password=samrat123
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUserDetails(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		List<UserDetail> userList = null;

		try {
			userList = service.getUser(username, password);
		} catch (Exception e) {
			response.setStatus("failed");
			response.setErrorCode(402);
			response.setMessage(e.getCause().toString());
			e.printStackTrace();
		}
		if (userList == null) {
			response.setStatus("failed");
			response.setErrorCode(402);
			response.setMessage("Error processing the request...");
		}
		
		return userList != null ? new ResponseEntity<Object>(userList, HttpStatus.OK)
				: new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
}