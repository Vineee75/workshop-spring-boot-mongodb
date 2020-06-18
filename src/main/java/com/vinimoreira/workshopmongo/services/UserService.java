package com.vinimoreira.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinimoreira.workshopmongo.domain.User;
import com.vinimoreira.workshopmongo.dto.UserDTO;
import com.vinimoreira.workshopmongo.repository.UserRepository;
import com.vinimoreira.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	// Métodos CRUD

	// C

	public User insert(User obj) {
		return repo.insert(obj);
	}

	// R

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}

	// U

	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	// D

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	// Fim dos métodos CRUD

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
