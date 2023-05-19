package com.financial.service;

import org.springframework.stereotype.Service;

import com.financial.dto.BankAccountDto;
import com.financial.exception.BankAccountNotFoundException;
import com.financial.exception.UserNotFoundException;

@Service
public interface BankAccountService {

	BankAccountDto addBankDetails(BankAccountDto bankAccountDto, Integer userid)
			throws UserNotFoundException, BankAccountNotFoundException;

	public String removeBankAccountDetails(Integer userid, Integer bankId)
			throws UserNotFoundException, BankAccountNotFoundException;

	public BankAccountDto updateBankAccountDetails(Integer userId, Integer bankId, BankAccountDto bankAccountDto)
			throws UserNotFoundException, BankAccountNotFoundException;

}
