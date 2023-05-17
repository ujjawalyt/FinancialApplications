package com.financial.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.financial.dto.UserDto;
import com.financial.exception.UserNotFoundException;

@Service
public interface UserService {

	
	public UserDto registerNewUser(UserDto userDto)  throws UserNotFoundException;
	public UserDto updateUser(UserDto userDto, Integer userid)throws UserNotFoundException;
	public String deleteExistingUser(Integer userid)throws UserNotFoundException;
	public List<UserDto> getAllUser();
	public UserDto getUserById(Integer userid)throws UserNotFoundException;
	
	
}
