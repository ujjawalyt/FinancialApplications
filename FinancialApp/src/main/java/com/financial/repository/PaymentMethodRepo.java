package com.financial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financial.entities.PaymentMethods;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethods, Integer> {

	List<PaymentMethods> findByUsersUserId(Integer userId);

}
