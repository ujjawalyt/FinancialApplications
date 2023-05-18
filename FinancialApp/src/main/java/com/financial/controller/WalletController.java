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

import com.financial.dto.WalletsDto;
import com.financial.exception.UserNotFoundException;
import com.financial.exception.WalletAlreadyExistsException;
import com.financial.exception.WalletNotFoundException;
import com.financial.service.WalletsService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	@Autowired
	private WalletsService walletsService;
	
	@PostMapping("/create/user/{userid}")
	public ResponseEntity<WalletsDto> createWalletByUser
	(@RequestBody WalletsDto walletsDto, @PathVariable("userid")Integer userid) throws UserNotFoundException,WalletAlreadyExistsException{
		
		return new ResponseEntity<WalletsDto>(walletsService.createWalletByUser(walletsDto, userid),HttpStatus.CREATED);
				
	}
	
	@DeleteMapping("/delete/user/{userid}/wallet/{walletid}")
	public ResponseEntity<String> deletWalletByUser
	( @PathVariable("userid")Integer userid,  @PathVariable("walletid")Integer walletid) 
			throws UserNotFoundException, WalletNotFoundException{
		
		return new ResponseEntity<String>(walletsService.deletWalletFromUser(userid, walletid),HttpStatus.ACCEPTED);
				
	}
	
	@PutMapping("/update/user/{userid}/amount/{amount}")
	public ResponseEntity<String> updateBalanceByUser( @PathVariable("userid")Integer userid, 
			 @PathVariable("amount") Double amount
			) throws UserNotFoundException,WalletNotFoundException
	{
		return new ResponseEntity<String>(walletsService.addBalanceToWallet(userid, amount),HttpStatus.OK);
	}
	
}
