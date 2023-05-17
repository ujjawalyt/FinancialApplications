package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entities.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

	Users findByEmail(String email);

}
