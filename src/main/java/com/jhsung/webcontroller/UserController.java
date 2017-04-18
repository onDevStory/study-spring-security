package com.jhsung.webcontroller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhsung.entity.User;
import com.jhsung.repository.UserRepository;
import com.jhsung.service.UserService;

@RestController
public class UserController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User saveUser(@RequestBody User user) {
		return User.secured(userService.saveUser(user));
	}

	// TODO ADMIN 에게만 권한부여(내부 SPY), 정렬기능 추가
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> findAll() {
		return User.secured(userRepository.findAll());
	}

	/**
	 * http://localhost:8080/users/id?q=1
	 * http://localhost:8080/users/email?q=2@gmail.com
	 * http://localhost:8080/users/user-name?q=성정환
	 * http://localhost:8080/users/verified?q=false
	 */
	@RequestMapping(value = "/users/{columnName}", method = RequestMethod.GET)
	public List<User> findColumnEqualValue(@PathVariable("columnName") String columnName, @RequestParam String q) {
		return User.secured(userService.findColumnEqualValue(columnName, q));
	}

}
