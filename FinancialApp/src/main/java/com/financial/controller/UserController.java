package com.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.dto.UserDto;
import com.financial.exception.UserNotFoundException;
import com.financial.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto) throws UserNotFoundException{
		return new ResponseEntity<UserDto>(userService.registerNewUser(userDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{userid}")
	public ResponseEntity<UserDto> updateExistingUser(@RequestBody UserDto userDto, @PathVariable("userid") Integer userid) 
			throws UserNotFoundException{
		return new ResponseEntity<UserDto>(userService.updateUser(userDto, userid),HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<String> deleteExistingUser( @PathVariable("userid") Integer userid) 
			throws UserNotFoundException{
		return new ResponseEntity<String>(userService.deleteExistingUser(userid),HttpStatus.OK);
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto> getExistingUser( @PathVariable("userid") Integer userid) 
			throws UserNotFoundException{
		return new ResponseEntity<UserDto>(userService.getUserById(userid),HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>> getAllExistingUser() 
			throws UserNotFoundException{
		return new ResponseEntity<List<UserDto>>(userService.getAllUser(),HttpStatus.OK);
	}
}
