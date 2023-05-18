package com.financial.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financial.dto.UserDto;
import com.financial.dto.WalletsDto;
import com.financial.entities.Users;
import com.financial.entities.Wallets;
import com.financial.exception.UserNotFoundException;
import com.financial.exception.WalletAlreadyExistsException;
import com.financial.exception.WalletNotFoundException;
import com.financial.repository.UsersRepo;
import com.financial.repository.WalletsRepo;

@Service
public class WalletServiceImpl implements WalletsService {

	@Autowired
	private WalletsRepo walletsRepo;
	
	@Autowired
	private UsersRepo usersRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public WalletsDto createWalletByUser(WalletsDto walletsDto, Integer userid) throws UserNotFoundException,WalletAlreadyExistsException {
		Users users = usersRepo.findById(userid).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userid));
	
		
		  boolean walletExists = walletsRepo.existsByUsers(users);
		    if (walletExists) {
		        throw new WalletAlreadyExistsException("Wallet already exists for user with ID: " + userid);
		    }
		
		Wallets wallets = modelMapper.map(walletsDto, Wallets.class);
		wallets.setUsers(users);
	Wallets saveWallet = 	walletsRepo.save(wallets);
	WalletsDto walletsDto2 = modelMapper.map(saveWallet, WalletsDto.class);
	
	UserDto userDto = modelMapper.map(users, UserDto.class);
	walletsDto2.setUserDto(userDto);
		return  walletsDto2;
	}

	@Override
	public String deletWalletFromUser(Integer userid, Integer walletId)
			throws UserNotFoundException, WalletNotFoundException {
		Users users = usersRepo.findById(userid)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userid));
		Wallets wallets = walletsRepo.findById(walletId)
				.orElseThrow(()-> new WalletNotFoundException("Wallet not found with Id :"+ walletId));
		 if (!wallets.getUsers().equals(users)) {
		        throw new WalletNotFoundException("Wallet not found with ID: " + walletId + " for user with ID: " + userid);
		    }

		
		wallets.setUsers(null);
		walletsRepo.delete(wallets);
		
		
		return "Wallet Removed Successfully..";
	}

	@Override
	public String addBalanceToWallet(Integer userid, Double amount)
			throws UserNotFoundException, WalletNotFoundException {
		
		Users users = usersRepo.findById(userid).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userid));
	
		List<Wallets> wallets = walletsRepo.findByUsers(users);
	    if (wallets.isEmpty()) {
	        throw new WalletNotFoundException("No wallets found for user with ID: " + userid);
	    }

	    for (Wallets wallet : wallets) {
	        Double currentBalance = wallet.getBalance();
	        Double finalBalance = currentBalance + amount;
	        wallet.setBalance(finalBalance);
	        walletsRepo.save(wallet);
	    }

	    return "Amount added - " + amount + " to all wallets successfully";
	    
//	     this is the second methode if we provide wallet id
//	    Wallets wallet = walletsRepo.findByIdAndUsers(walletId, users);
//	    if (wallet == null) {
//	        throw new WalletNotFoundException("Wallet not found with ID: " + walletId + " for user with ID: " + userid);
//	    }
	
	}
	
}
