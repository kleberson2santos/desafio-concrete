package com.concrete.desafio.security;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class SecurityToken {

	Key key = MacProvider.generateKey();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

	public String generate(String password) {
		String token = null;

		String issuer = "login";
		String id = String.valueOf(Math.random());

		token = Jwts.builder().setSubject(password).setId(id).setIssuer(issuer).signWith(SignatureAlgorithm.HS512, key)
				.compact();

		return token;
	}

	public String decode(String token) {
		Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		Claims body = parseClaimsJws.getBody();

		String issuer = body.getIssuer();
		Date expiration = body.getExpiration();
		System.out.println("expiration = " + expiration);

		String id = body.getId();
		String subject = body.getSubject();

		

		System.out.println("issuer = " + issuer);
		System.out.println("id = " + id);
		System.out.println("subject = " + subject);

		return subject;
	}

	
}