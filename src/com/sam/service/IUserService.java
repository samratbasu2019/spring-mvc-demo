package com.sam.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sam.model.UserDetail;


public interface IUserService {
	public boolean addUser(UserDetail userdetails);
	public List<UserDetail> getUser(String username,String password);
}
