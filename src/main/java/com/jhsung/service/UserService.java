package com.jhsung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.jhsung.common.exception.CustomException;
import com.jhsung.common.exception.ErrorName;
import com.jhsung.entity.User;
import com.jhsung.entity.columns.UserColumn;
import com.jhsung.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	public User saveUser(User user) {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new CustomException(ErrorName.ALREADY_JOIN_EMAIL);
		}
		user.setPassword(getEncodedPassword(user.getPassword()));
		return userRepository.save(user);
	}

	public List<User> findColumnEqualValue(String columnName, String q) {
		switch (UserColumn.lookup(columnName)) {
		case ID:
			return Lists.newArrayList(userRepository.findById(Long.parseLong(q)));
		case EMAIL:
			return Lists.newArrayList(userRepository.findByEmail(q));
		case USER_NAME:
			return userRepository.findByUserName(q);
		case VERIFIED:
			return userRepository.findByVerified(Boolean.valueOf(q));
		default:
			return Lists.newArrayList();
		}
	}

	private String getEncodedPassword(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

}
