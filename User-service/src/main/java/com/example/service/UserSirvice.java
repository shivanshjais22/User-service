package com.example.service;

import com.example.dto.*;

public interface UserSirvice {
	 public UserDTO signup(SignUpRequestDTO signUpRequestDTO);


public LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
