package com.concrete.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concrete.desafio.domain.User;

public interface UsersRepository extends JpaRepository<User, Long>{

	public List<User> findByEmail(String email);

}
