package com.jhsung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jhsung.entity.User;
import com.jhsung.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	public User saveUser(User user) {
		user.setPassword(getEncodedPassword(user.getPassword()));
		return userRepository.save(user);
	}

	private String getEncodedPassword(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

}
