package com.jhsung.webcontroller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhsung.entity.User;
import com.jhsung.entity.dto.UserDto;
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

	@PostMapping("/isAvailableEmail")
	public boolean isAvailableEmail(@RequestBody @Valid UserDto.Email dto, BindingResult bindingResult) {
		return userRepository.findByEmail(dto.getEmail()).isEmpty();
	}

	@PostMapping("/users")
	public void saveUser(@RequestBody @Valid UserDto.ForCreate dto, BindingResult bindingResult) {
		userService.saveUser(modelMapper.map(dto, User.class));
	}

	@GetMapping("/users")
	public List<User> findAll() {
		// TODO security & sorting
		return userRepository.findAll();
	}

	/**
	 * http://localhost:8080/users/id?q=1
	 * http://localhost:8080/users/email?q=2@gmail.com
	 * http://localhost:8080/users/user-name?q=성정환
	 * http://localhost:8080/users/verified?q=false
	 */
	@GetMapping("/users/{columnName}")
	public List<User> findColumnEqualValue(@PathVariable("columnName") String columnName, @RequestParam String q) {
		// TODO except password & sorting
		return userService.findColumnEqualValue(columnName, q);
	}

}
