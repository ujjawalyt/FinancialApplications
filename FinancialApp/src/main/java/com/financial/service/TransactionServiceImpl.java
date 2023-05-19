package com.financial.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financial.dto.TransactionsDto;
import com.financial.entities.Transactions;
import com.financial.entities.Wallets;
import com.financial.exception.InsufficientBalanceException;
import com.financial.exception.InvalidAmountException;
import com.financial.exception.WalletNotFoundException;
import com.financial.repository.TransactionsRepo;
import com.financial.repository.UsersRepo;
import com.financial.repository.WalletsRepo;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private WalletsRepo walletsRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UsersRepo usersRepo;
	
	@Autowired
	private TransactionsRepo transactionsRepo;

	@Override
	public TransactionsDto sendMoneyToAnotherWallet(TransactionsDto transactionsDto, Integer senderWalletId,
			Integer receiverWalletId) throws WalletNotFoundException , InsufficientBalanceException, InvalidAmountException{
		
		 Wallets senderWallet = walletsRepo.findById(senderWalletId)
		            .orElseThrow(() -> new WalletNotFoundException("Wallet not found with this sender wallet ID: " + senderWalletId));

		    Wallets receiverWallet = walletsRepo.findById(receiverWalletId)
		            .orElseThrow(() -> new WalletNotFoundException("Wallet not found with this receiver wallet ID: " + receiverWalletId));

		    Transactions transactions = modelMapper.map(transactionsDto, Transactions.class);
		    transactions.setTimestamp(LocalDateTime.now());
		    Double amountToSend = transactions.getAmount();
		    if (senderWallet.getBalance() < amountToSend) {
		        throw new InsufficientBalanceException("Insufficient balance in sender's wallet");
		    }
		    
		    if (amountToSend <= 0) {
		        throw new InvalidAmountException("Invalid amount to send");
		    }

		    Double updatedSenderWalletAmount = senderWallet.getBalance() - amountToSend;
		    Double updatedReceiverWalletAmount = receiverWallet.getBalance() + amountToSend;

		    if (updatedSenderWalletAmount < 100) {
		        throw new InsufficientBalanceException("Minimum balance limit reached in sender's wallet");
		    }
		    
		    senderWallet.setBalance(updatedSenderWalletAmount);
		    receiverWallet.setBalance(updatedReceiverWalletAmount);
		    transactions.setSenderWallet(senderWallet);
		    transactions.setReceiverWallet(receiverWallet);
		    

		    walletsRepo.save(senderWallet);
		    walletsRepo.save(receiverWallet);

		    Transactions savedTransactions = transactionsRepo.save(transactions);
		    
		TransactionsDto transactionsDto2 =   modelMapper.map(savedTransactions, TransactionsDto.class);
		transactionsDto2.setSenderName(senderWallet.getUsers().getFirstName());
		transactionsDto2.setReceiverName(receiverWallet.getUsers().getFirstName());
		
		return transactionsDto2;
	}
	
}
