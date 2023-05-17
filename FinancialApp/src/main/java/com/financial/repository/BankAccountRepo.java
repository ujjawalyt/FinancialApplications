package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financial.entities.BankAccounts;

public interface BankAccountRepo extends JpaRepository<BankAccounts, Integer>{

}
