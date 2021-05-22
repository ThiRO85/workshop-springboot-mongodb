package com.ThiRO85.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ThiRO85.workshopmongo.domain.User;
import com.ThiRO85.workshopmongo.dto.UserDTO;
import com.ThiRO85.workshopmongo.repositories.UserRepository;
import com.ThiRO85.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired //@Autowired é um mecanismo de injeção de dependência automática do Spring. Elide a necessidade de se instanciar o objeto!
	private UserRepository repo; //Serviço acessa o repositório

	public List<User> findAll() {
		return repo.findAll(); //Busca na biblioteca do Spring um método!
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
