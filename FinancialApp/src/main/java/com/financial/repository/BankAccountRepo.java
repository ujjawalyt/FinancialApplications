package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financial.entities.BankAccounts;
import com.financial.entities.Users;

public interface BankAccountRepo extends JpaRepository<BankAccounts, Integer>{

	boolean existsByUsers(Users users);

	boolean existsByUsersAndAccountNumber(Users users, String accountNumber);

}
