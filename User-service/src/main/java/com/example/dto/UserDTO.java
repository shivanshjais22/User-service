package com.example.dto;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.example.model.Role;

@Getter
@Setter
public class UserDTO {
    private String email;
    private String name;
    private Role role;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
