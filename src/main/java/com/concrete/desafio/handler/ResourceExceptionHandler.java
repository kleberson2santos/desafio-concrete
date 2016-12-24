package com.concrete.desafio.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.concrete.desafio.domain.DetailsErrors;
import com.concrete.desafio.services.exceptions.UsuarioNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<DetailsErrors> handleUsuarioNaoEncontradoException
							(UsuarioNaoEncontradoException e, HttpServletRequest request) {
		
		DetailsErrors erro = new DetailsErrors();
		erro.setStatus(404l);
		erro.setTitulo("404 - O usuario não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}