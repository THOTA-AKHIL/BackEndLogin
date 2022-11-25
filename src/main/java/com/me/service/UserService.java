package com.me.service;

import com.me.dto.UserDTO;
import com.me.entity.User;
import com.me.exceptions.UserNameAlreadyExistingException;
import com.me.exceptions.UserNotFoundException;

public interface UserService {
//	User save(User user);

	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);
	
	public String userRegistration (User user) throws UserNameAlreadyExistingException;
	
	public UserDTO login(String userName,String password) throws UserNotFoundException;
	
}
