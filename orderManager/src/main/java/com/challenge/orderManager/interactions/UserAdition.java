package com.challenge.orderManager.interactions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.orderManager.dtos.UserDTO;
import com.challenge.orderManager.entities.User;
import com.challenge.orderManager.repositories.UserRepository;

@Service
public class UserAdition {

	@Autowired
	private UserRepository repository;

	public User save(UserDTO user) throws Exception {
		isUserValid(user);
		return repository.save(user.toEntity());
	}

	private boolean isUserTypeInvalid(UserDTO user) throws Exception {
		if (UserType.ADMIN.name().equals(user.getType())) {
			return false;
		} else if (UserType.ATTENDANT_BOX.name().equals(user.getType())) {
			return false;
		} else if (UserType.COOK.name().equals(user.getType())) {
			return false;
		} else if (UserType.WAITER.name().equals(user.getType())) {
			return false;
		} else {
			return true;
		}
	}

	private void isUserValid(UserDTO user) throws Exception {
		if (user.getName() == null) {
			throw new Exception("Campo nome é obrigatorio");
		}
		if (user.getName().trim().length() < 2) {
			throw new Exception("O nome do usuário deve conter no minimo 3 caracteres");
		}
		if (user.getName().length() > 50) {
			throw new Exception("O nome do usuário não pode ter mais que 50 caracteres");
		}
		if (user.getEmail() == null) {
			throw new Exception("Campo email é obrigatorio");
		}
		if (user.getEmail().length() > 50) {
			throw new Exception("O email não pode ter mais que 50 caracteres");
		}
		if (isEmailInvalid(user.getEmail())) {
			throw new Exception("O email informado não é valido");
		}
		if (user.getPassword() == null) {
			throw new Exception("Campo senha é obrigatorio");
		}
		if (user.getPassword().length() > 50) {
			throw new Exception("A senha não pode ter mais que 50 caracteres");
		}
		if (user.getPassword().trim().length() < 6) {
			throw new Exception("A senha deve conter no minimo 6 caracteres");
		}
		if (user.getType().trim().length() > 50) {
			throw new Exception("O tipo de usuário não pode ter mais que 50 caracteres");
		}
		if (isUserTypeInvalid(user)) {
			throw new Exception("Não foi possivel salvar o usuário pois o tipo dele é invalido");
		}			
	}

	private boolean isEmailInvalid(String email) {		
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				return false;
			}
		}
		return true;
	}

}
