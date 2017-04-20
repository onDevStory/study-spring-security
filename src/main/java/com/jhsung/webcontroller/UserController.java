package com.jhsung.webcontroller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/isAvailableEmail", method = RequestMethod.POST)
	public boolean isAvailableEmail(@RequestBody @Valid UserDto.Email dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// throw new CustomException();
		}
		return userRepository.findByEmail(dto.getEmail()).isEmpty();
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String saveUser(@RequestBody @Valid UserDto.ForCreate dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// throw new CustomException();
		}
		// email 중복 시, ConstraintViolationException 500 Error 처리
		return userService.saveUser(modelMapper.map(dto, User.class)) != null ? "success" : "fail";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
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
	@RequestMapping(value = "/users/{columnName}", method = RequestMethod.GET)
	public List<User> findColumnEqualValue(@PathVariable("columnName") String columnName, @RequestParam String q) {
		// security & sorting
		return userService.findColumnEqualValue(columnName, q);
	}

}
