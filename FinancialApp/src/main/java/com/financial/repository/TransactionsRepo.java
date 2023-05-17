package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entities.Transactions;
@Repository
public interface TransactionsRepo extends JpaRepository<Transactions, Integer> {

}
