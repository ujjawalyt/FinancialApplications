package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entities.Wallets;

@Repository
public interface WalletsRepo extends JpaRepository<Wallets, Integer> {

}
