package com.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dto.UserDTO;
import com.me.entity.User;
import com.me.exceptions.UserNameAlreadyExistingException;
import com.me.exceptions.UserNotFoundException;
import com.me.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	public User save(User user) {
		return userRepo.save(user);
}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		
		return userRepo.findByEmailAndPassword(email,password);
	}
	
	
	

	@Override
	public String userRegistration(User user)throws UserNameAlreadyExistingException{
		String message=null;
		User user1=null;
		if(this.userRepo.existsByFirstName(user.getFirstName())) {
			throw new UserNameAlreadyExistingException("user with given username already exist");
		}
		user1=userRepo.save(user);
		if(user1!=null) {
			message="Registered Successfully";
			
		}
		return message;
	}

@Override
public UserDTO login(String userName, String password) throws UserNotFoundException {
	// TODO Auto-generated method stub
	return null;
}
	
	
	

}
