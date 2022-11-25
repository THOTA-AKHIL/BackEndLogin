package com.me.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.dto.UserDTO;
import com.me.entity.User;
import com.me.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;


	
	@PostMapping(value = "/registerUser")
	public String registerUser(@RequestBody User user) {

		return userService.userRegistration(user);
	}

	@PostMapping(path = "/login")
	public String validate(@RequestBody User user) throws ServletException {
		String jwtToken = "";

		if (user.getEmail() == null || user.getPassword() == null) {
			throw new ServletException("Please fill in username and password");
		}

		String email = user.getEmail();
		String password = user.getPassword();

		user = userService.findByEmailAndPassword(email, password);

		if (user == null) {
			throw new ServletException("User email not found.");
		}

		String pwd = user.getPassword();

		if (!password.equals(pwd)) {
			throw new ServletException("Invalid login. Please check your name and password.");
		}

		jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		return jwtToken;
	}

	@GetMapping("/message")
	public String returnMessage() {
		return "Login Successfull!";
	}

}
