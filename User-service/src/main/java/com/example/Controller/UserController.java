package com.example.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.dto.*;
import com.example.service.*;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
	UserSirvice userService;
	    @PostMapping("/signup")
	    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO)throws RuntimeException{
	        UserDTO userDTO = userService.signup(signUpRequestDTO);
	        return new ResponseEntity<>(userDTO, HttpStatus.OK);
	    }
}
