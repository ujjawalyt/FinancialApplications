package com.financial.service;

import org.springframework.stereotype.Service;

import com.financial.dto.TransactionsDto;
import com.financial.exception.InsufficientBalanceException;
import com.financial.exception.InvalidAmountException;
import com.financial.exception.UserNotFoundException;
import com.financial.exception.WalletNotFoundException;

@Service
public interface TransactionService {

	public TransactionsDto sendMoneyToAnotherWallet(TransactionsDto transactionsDto, Integer
			senderWalletId ,Integer receiverWalletId) 
					throws WalletNotFoundException,InsufficientBalanceException,InvalidAmountException ;
			
			
}
