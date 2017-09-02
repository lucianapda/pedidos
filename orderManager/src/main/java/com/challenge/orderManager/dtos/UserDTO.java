package com.challenge.orderManager.dtos;

import com.challenge.orderManager.entities.Product;
import com.challenge.orderManager.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String id;
	private String name;
	private String type;
	private String email;
	private String password;
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.password = user.getPassword();
		this.type = user.getType();
		this.email = user.getEmail();
	}
	
	public User toEntity() {
		return User.builder().id(this.id).name(this.name).email(this.email).type(this.type).password(this.password).build();
	}
}
