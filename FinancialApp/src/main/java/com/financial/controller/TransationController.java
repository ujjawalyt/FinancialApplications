package com.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.dto.TransactionsDto;
import com.financial.exception.InsufficientBalanceException;
import com.financial.exception.InvalidAmountException;
import com.financial.exception.WalletNotFoundException;
import com.financial.service.TransactionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/transation")
public class TransationController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/senderWallet/{senderWalletId}/receiverWallet/{receiverWalletId}")
	public ResponseEntity<TransactionsDto> sendMoneyToAnotherWallet(
	@RequestBody TransactionsDto transactionsDto, @PathVariable("senderWalletId") Integer senderWalletId,
	@PathVariable("receiverWalletId") Integer receiverWalletId)
	throws WalletNotFoundException, InsufficientBalanceException,InvalidAmountException {
		
		return new ResponseEntity<TransactionsDto>(transactionService.sendMoneyToAnotherWallet(transactionsDto, 
				senderWalletId, receiverWalletId),HttpStatus.CREATED);
		
	}
}
