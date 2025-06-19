package com.example.service;

import com.example.dto.LoginRequestDTO;
import org.springframework.security.core.Authentication;


public interface TokenService {
	public String generateToken(Authentication authentication);
}
