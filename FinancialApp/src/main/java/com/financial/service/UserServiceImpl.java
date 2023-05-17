package com.financial.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financial.dto.UserDto;
import com.financial.entities.Users;
import com.financial.exception.UserNotFoundException;
import com.financial.repository.UsersRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepo usersRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto registerNewUser(UserDto userDto) throws UserNotFoundException {
		
		Users existingUser = usersRepo.findByEmail(userDto.getEmail());
	    if (existingUser != null) {
	        throw new UserNotFoundException("User with email " + userDto.getEmail() + " already exists.");
	    }
	   Users user = modelMapper.map(userDto, Users.class) ;
	   Users saveUser =    usersRepo.save(user);
	   return modelMapper.map(saveUser, UserDto.class);
		
		
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userid) throws UserNotFoundException {
		
		 Users user = usersRepo.findById(userid).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userid));

		  
		    user.setEmail(userDto.getEmail());
		    user.setFirstName(userDto.getFirstName());
		    user.setLastName(userDto.getLastName());
		    user.setPassword(userDto.getPassword());
		    user.setPhone(userDto.getPhone());
		    

		    Users updatedUser = usersRepo.save(user); 

		    return modelMapper.map(updatedUser, UserDto.class);
	
	}

	@Override
	public String deleteExistingUser(Integer userid) throws UserNotFoundException {
		Users users = usersRepo.findById(userid).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userid));
	    usersRepo.delete(users);
	    return "User deleted successfully";
	}

	@Override
	public List<UserDto> getAllUser() {
		 List<Users> users = usersRepo.findAll();
		 
	List<UserDto> saved = 	 users.stream().map((u)-> modelMapper
			.map(u, UserDto.class)).collect(Collectors.toList());
	
	return saved;
		
		 
		 
	}

	@Override
	public UserDto getUserById(Integer userid) throws UserNotFoundException {
		 Users users = usersRepo.findById(userid).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userid));
		    return modelMapper.map(users, UserDto.class);
	}
	
	
}
