package com.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.dto.BankAccountDto;
import com.financial.exception.BankAccountNotFoundException;
import com.financial.exception.UserNotFoundException;
import com.financial.service.BankAccountService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController

public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;
	
	@PostMapping("addbankAccount/user/{userid}")
	public ResponseEntity<BankAccountDto> addBankToUser
	(@RequestBody BankAccountDto bankAccountDto, @PathVariable("userid") Integer userid)
	throws UserNotFoundException,BankAccountNotFoundException{
		
		return new ResponseEntity<BankAccountDto>
		(bankAccountService.addBankDetails(bankAccountDto, userid),HttpStatus.CREATED);
		
	}
	
	 @DeleteMapping("delete/user/{userid}/bank/{bankId}")
	 public ResponseEntity<String> removeBankAccount(@PathVariable("userid") Integer userid, @PathVariable("bankId") Integer bankId)
	    		throws UserNotFoundException, BankAccountNotFoundException{
		 
		 return new ResponseEntity<String>(bankAccountService.removeBankAccountDetails(userid, bankId),HttpStatus.OK);
	       
	  }
	 
@PutMapping("update/user/{userid}/bank/{bankId}")
	    public ResponseEntity<BankAccountDto> updateBankAccount(@PathVariable("userid") Integer userId, @PathVariable("bankId") Integer bankId, @RequestBody BankAccountDto bankAccountDto) 

	    		throws UserNotFoundException, BankAccountNotFoundException{
	        
	return new ResponseEntity<BankAccountDto>(bankAccountService.updateBankAccountDetails(userId, bankId, bankAccountDto),HttpStatus.OK);
	   }
	
	
			
}
