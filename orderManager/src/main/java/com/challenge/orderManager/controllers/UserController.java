package com.challenge.orderManager.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.orderManager.dtos.UserDTO;
import com.challenge.orderManager.dtos.UserListDTO;
import com.challenge.orderManager.interactions.UserAdition;
import com.challenge.orderManager.repositories.UserRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAdition userAdition;

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO get(@PathVariable String userId) {
		return new UserDTO(userRepository.findOne(userId));
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserListDTO list() {
		return new UserListDTO(userRepository.getUserList().stream().map(UserDTO::new).collect(Collectors.toList()));
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO save(@RequestBody UserDTO userDTO) throws Exception {
		return new UserDTO(userAdition.save(userDTO));
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String userId) {
		userRepository.delete(userId);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO merge(@RequestBody UserDTO userDTO) throws Exception{
		return new UserDTO(userAdition.save(userDTO));
	}
}
