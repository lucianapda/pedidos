package com.challenge.orderManager.dtos;

import com.challenge.orderManager.dtos.LoginDTO.LoginDTOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class tokenDTO {

	private String token;
}
