package com.financial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entities.Users;
import com.financial.entities.Wallets;

@Repository
public interface WalletsRepo extends JpaRepository<Wallets, Integer> {

	boolean existsByUsers(Users users);

	List<Wallets> findByUsers(Users users);

}
