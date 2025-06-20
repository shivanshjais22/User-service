package com.example.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import com.example.model.User;
import com.example.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService {
	 public CustomUserDetailsService() {
		super();
		// TODO Auto-generated constructor stub
	}


	 public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	 UserRepository userRepository;

	   
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByEmail(username)
	                .orElseThrow(() -> new UsernameNotFoundException("Email not found with " + username));
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
	        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getHashedPassword(),authorities);
	    }
}
