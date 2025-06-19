package com.example.service;


import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.constants.ApplicationConstants;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtTokenServiceImpl  implements TokenService{

	@Override
	public String generateToken(Authentication authentication) {
		  SecretKey key = Keys.hmacShaKeyFor(ApplicationConstants.SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	        return Jwts.builder()
	                .setSubject(ApplicationConstants.SUBJECT)
	                .claim("email", authentication.getName())
	                .claim("role", authentication.getAuthorities())
	                .setIssuer(ApplicationConstants.ISSUER)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + ApplicationConstants.EXPIRATION_TIME)) // Set expiration
	                .signWith(key)
	                .compact();
	}

}
