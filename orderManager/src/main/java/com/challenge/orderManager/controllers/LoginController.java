package com.challenge.orderManager.controllers;

import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.orderManager.dtos.LoginDTO;
import com.challenge.orderManager.dtos.UserDTO;
import com.challenge.orderManager.dtos.UserListDTO;
import com.challenge.orderManager.dtos.tokenDTO;
import com.challenge.orderManager.entities.User;
import com.challenge.orderManager.interactions.UserAdition;
import com.challenge.orderManager.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public tokenDTO login(@RequestBody LoginDTO login) throws Exception {
		String jwtToken = "";

		if (login.getEmail() == null || login.getPassword() == null) {
			throw new ServletException("Please fill in username and password");
		}

		String email = login.getEmail();
		String password = login.getPassword();

		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new ServletException("User email not found.");
		}

		String pwd = user.getPassword();

		if (!password.equals(pwd)) {
			throw new ServletException("Invalid login. Please check your name and password.");
		}

		jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		return new tokenDTO(jwtToken);
	}
}
