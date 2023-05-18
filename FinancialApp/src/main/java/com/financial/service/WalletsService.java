package com.financial.service;

import org.springframework.stereotype.Service;

import com.financial.dto.WalletsDto;
import com.financial.exception.UserNotFoundException;
import com.financial.exception.WalletAlreadyExistsException;
import com.financial.exception.WalletNotFoundException;

@Service
public interface WalletsService {

	public WalletsDto createWalletByUser(WalletsDto walletsDto , Integer userid)
         throws UserNotFoundException,WalletAlreadyExistsException;
	public String deletWalletFromUser(Integer userid, Integer walletId)
			throws UserNotFoundException ,WalletNotFoundException;
	public String addBalanceToWallet(Integer userid,  Double amount)
			throws UserNotFoundException ,WalletNotFoundException;
}
