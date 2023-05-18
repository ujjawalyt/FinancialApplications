package com.financial.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financial.dto.BankAccountDto;
import com.financial.dto.UserDto;
import com.financial.entities.BankAccounts;
import com.financial.entities.Users;
import com.financial.exception.BankAccountNotFoundException;
import com.financial.exception.UserNotFoundException;
import com.financial.repository.BankAccountRepo;
import com.financial.repository.UsersRepo;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private UsersRepo usersRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BankAccountRepo bankAccountRepo;

	@Override
	public BankAccountDto addBankDetails(BankAccountDto bankAccountDto, Integer userid)
			throws UserNotFoundException, BankAccountNotFoundException {
		
		Users users = usersRepo.findById(userid).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userid));
		
		 boolean bankAccountExists = bankAccountRepo.existsByUsersAndAccountNumber(users, bankAccountDto.getAccountNumber());
		    if (bankAccountExists) {
		        throw new BankAccountNotFoundException("Bank account already exists with account number '" + bankAccountDto.getAccountNumber() + "' for user with ID: " + userid);
		    }
		
		BankAccounts bankAccounts = modelMapper.map(bankAccountDto, BankAccounts.class);
		bankAccounts.setUsers(users);
		BankAccounts savedBankAccounts = bankAccountRepo.save(bankAccounts);
		
		BankAccountDto bankAccountDto2 = modelMapper.map(savedBankAccounts, BankAccountDto.class);
		
		UserDto userDto = modelMapper.map(users, UserDto.class);
		bankAccountDto2 .setUserDto(userDto);
		return  bankAccountDto2;
		
	}

	@Override
	public String removeBankAccountDetails(Integer userid, Integer bankId)
			throws UserNotFoundException, BankAccountNotFoundException {
		Users users = usersRepo.findById(userid).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userid));
		
		BankAccounts bankAccounts = bankAccountRepo.findById(bankId)
				.orElseThrow(()-> new BankAccountNotFoundException("Bank Account id not found with this Id :"+ bankId));
		
		
		if (!bankAccounts.getUsers().equals(users)) {
	        throw new BankAccountNotFoundException("Bank account not found with ID: " + bankId + " for user with ID: " + userid);
	    }
		
		bankAccounts.setUsers(null);
		 bankAccountRepo.delete(bankAccounts);

		 return "Bank account removed successfully.";
	}

	@Override
	public BankAccountDto updateBankAccountDetails(Integer userId, Integer bankId , BankAccountDto bankAccountDto)
			throws UserNotFoundException, BankAccountNotFoundException {
		Users users = usersRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
		BankAccounts bankAccounts = bankAccountRepo.findById(bankId)
				.orElseThrow(()-> new BankAccountNotFoundException("Bank Account id not found with this Id :"+ bankId));
		
		
		if (!bankAccounts.getUsers().equals(users)) {
	        throw new BankAccountNotFoundException("Bank account not found with ID: " + bankId + " for user with ID: " + userId);
	    }
		
		bankAccounts.setAccountNumber(bankAccountDto.getAccountNumber());
		bankAccounts.setBankName(bankAccountDto.getBankName());
		bankAccounts.setIfscCode(bankAccountDto.getIfscCode());	
	   
	    BankAccounts updatedBankAccounts = bankAccountRepo.save(bankAccounts);
	    BankAccountDto updatedBankAccountDto = modelMapper.map(updatedBankAccounts, BankAccountDto.class);
	    
	    UserDto userDto = modelMapper.map(users, UserDto.class);
	    updatedBankAccountDto.setUserDto(userDto);
	    
	    return updatedBankAccountDto;
		
		
		
		
	}
	
}
