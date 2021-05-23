package com.ThiRO85.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ThiRO85.workshopmongo.domain.Post;
import com.ThiRO85.workshopmongo.repositories.PostRepository;
import com.ThiRO85.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired //@Autowired é um mecanismo de injeção de dependência automática do Spring. Elide a necessidade de se instanciar o objeto!
	private PostRepository repo; //Serviço acessa o repositório

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() * 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
