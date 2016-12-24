package com.concrete.desafio.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.concrete.desafio.domain.User;
import com.concrete.desafio.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersResources {
	
	@Autowired
	private UsersService usersService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(usersService.listar());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> salvar(@RequestBody User user) {
		user = usersService.salvar(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		User user = usersService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody User user, 
			@PathVariable("id") Long id) {
		user.setId(id);
		usersService.atualizar(user);
		
		return ResponseEntity.noContent().build();
	}
	
}
