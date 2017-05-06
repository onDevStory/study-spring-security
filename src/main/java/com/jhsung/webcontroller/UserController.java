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

import com.jhsung.common.exception.CustomException;
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

	@PostMapping(value = "/isAvailableEmail")
	public boolean isAvailableEmail(@RequestBody @Valid UserDto.Email dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new CustomException("validation error");
		}
		return userRepository.findByEmail(dto.getEmail()).isEmpty();
	}

	@PostMapping(value = "/users")
	public String saveUser(@RequestBody @Valid UserDto.ForCreate dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new CustomException("validation error");
		}
		// TODO email 중복 시, ConstraintViolationException 500 Error 처리
		return userService.saveUser(modelMapper.map(dto, User.class)) != null ? "success" : "fail";
	}

	@GetMapping(value = "/users")
	public List<User> findAll() {
		// security & sorting
		return userRepository.findAll();
	}

	/**
	 * http://localhost:8080/users/id?q=1
	 * http://localhost:8080/users/email?q=2@gmail.com
	 * http://localhost:8080/users/user-name?q=성정환
	 * http://localhost:8080/users/verified?q=false
	 */
	@GetMapping(value = "/users/{columnName}")
	public List<User> findColumnEqualValue(@PathVariable("columnName") String columnName, @RequestParam String q) {
		// TODO except password & sorting
		return userService.findColumnEqualValue(columnName, q);
	}

}
