package com.concrete.desafio.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.concrete.desafio.domain.Credential;
import com.concrete.desafio.domain.User;
import com.concrete.desafio.services.UsersService;

@RestController
@RequestMapping("/login")
public class LoginResources {
	
	@Autowired
	private UsersService usersService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> buscar(@RequestBody Credential credential) {
		
			User user = usersService.login(credential);
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
		}
	
}
