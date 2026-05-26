package com.medicare.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.auth_service.dto.AuthRequest;
import com.medicare.auth_service.dto.AuthResponse;
import com.medicare.auth_service.entity.User;
import com.medicare.auth_service.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
    @Autowired
	private UserService userService;
    
    @PostMapping("/register")
    public User register(@RequestBody User user) {
    	return userService.saveUser(user);
    }
    
    @GetMapping("/test")
    public String test() {
        return "Auth service working";
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
    	String token = userService.login(request);
    	return new AuthResponse(token);
    }
}
