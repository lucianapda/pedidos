package com.challenge.orderManager.dtos;

import com.challenge.orderManager.dtos.UserDTO.UserDTOBuilder;
import com.challenge.orderManager.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

	private String email;
	private String password;
}
