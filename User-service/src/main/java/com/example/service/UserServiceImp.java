package com.example.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.*;
import com.example.model.*;
import org.springframework.security.core.Authentication;
import com.example.repository.TokenRepository;
import com.example.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserServiceImp implements UserSirvice {
	UserRepository userRepository;
	 BCryptPasswordEncoder bCryptPasswordEncoder;
	 EmailService emailService;
	 AuthenticationManager authenticationManager;
	 TokenService tokenService;
	 TokenRepository tokenRepository;
	@Override
	public UserDTO signup(SignUpRequestDTO signUpRequestDTO) {
		  if(!emailService.isValidEmail(signUpRequestDTO.getEmail())){
	            throw new RuntimeException("Email is not valid");
	        }
	        User user = from(signUpRequestDTO);
	        User savedUser = userRepository.save(user);
	        return from(savedUser);

	}
	
	 public User from (SignUpRequestDTO signUpRequestDTO){
	        User user = new User();
	        Role role = new Role();
	        user.setEmail(signUpRequestDTO.getEmail());
	        user.setName(signUpRequestDTO.getName());
	        user.setHashedPassword(bCryptPasswordEncoder.encode(signUpRequestDTO.getPassword()));
	        role.setValue(signUpRequestDTO.getRole());
	        user.setRole(role);
	        user.setEmailVerified(true);
	        return user;
	    }
	    public UserDTO from(User user){
	        UserDTO userDTO = new UserDTO();
	        userDTO.setEmail(user.getEmail());
	        userDTO.setName(user.getName());
	        userDTO.setRole(user.getRole());
	        return userDTO;
	    }

		@Override
		   public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
	        Authentication authentication = UsernamePasswordAuthenticationToken
	                .unauthenticated(loginRequestDTO.email(), loginRequestDTO.password());
	        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
	        return Optional.ofNullable(authenticationResponse)
	                .filter(Authentication::isAuthenticated)
	                .map(auth->new LoginResponseDTO(auth.getName(),generateAndSaveJwtToken(auth,loginRequestDTO.email())))
	                .orElseThrow(()->new BadCredentialsException("Invalid Username Or Password"));
	    }
		
}
