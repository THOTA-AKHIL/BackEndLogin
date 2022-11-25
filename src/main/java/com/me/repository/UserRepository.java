package com.me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.entity.User;


@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    
    public User findByFirstNameAndPassword(String firstName,String password);
    public boolean existsByFirstName(String firstName);
    



	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
}
