package com.jhsung.webcontroller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jhsung.entity.User;
import com.jhsung.service.UserService;

@RestController
public class UserController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User saveUser(@RequestBody User user) {
		return User.secured(userService.saveUser(user));
	}

}
