package com.concrete.desafio.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.desafio.domain.Credential;
import com.concrete.desafio.domain.User;
import com.concrete.desafio.repository.UsersRepository;
import com.concrete.desafio.security.Encryption;
import com.concrete.desafio.security.SecurityToken;
import com.concrete.desafio.services.exceptions.UsuarioNaoEncontradoException;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	private SecurityToken securityToken = new SecurityToken();
	
	public List<User> listar() {
		return usersRepository.findAll();
	}
	
	public User buscar(Long id) {
		User user = usersRepository.findOne(id);
		
		if(user == null) {
			throw new UsuarioNaoEncontradoException("O usuario não pôde ser encontrado.");
		}
		
		return user;
	}
	
	public User salvar(User user) {
		user.setId(null);
		user.setCreated(new Date());
		user.setLast_login(new Date());
		user.setPassword(Encryption.encrypt(user.getPassword()));
		String token = securityToken.generate(user.getPassword());
		user.setToken(token);
		return usersRepository.save(user);
	}


	public User atualizar(User userModified) {
		buscar(userModified.getId());
		userModified.setLast_login(new Date());
		userModified.setCreated(userModified.getCreated());
		userModified.setToken(userModified.getToken());
		
		return usersRepository.save(userModified);
	}
	
	

	public User login(Credential credential) {
		List<User> users = usersRepository.findByEmail(credential.getEmail());
		if(users.isEmpty()) {
			throw new UsuarioNaoEncontradoException("O usuario não pôde ser encontrado.");
		}else if(!(users.get(0).getPassword()).equals(Encryption.encrypt(credential.getPassword()))){
			throw new UsuarioNaoEncontradoException("Usuário e/ou senha inválidos");
		}
		users.get(0).setPassword(credential.getPassword());
		users.get(0).setLast_login(new Date());

		return users.get(0);
	}
	
}
