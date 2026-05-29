package com.medicare.auth_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medicare.auth_service.config.JwtService;
import com.medicare.auth_service.dto.AuthRequest;
import com.medicare.auth_service.entity.User;
import com.medicare.auth_service.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User saveUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	public String login(AuthRequest request) {

		Optional<User> user = userRepository.findByUsername(request.getUsername());

		if (user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {

			return jwtService.generateToken(request.getUsername());
		}

		return "Invalid username or password";
	}
}