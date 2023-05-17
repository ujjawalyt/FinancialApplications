package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financial.entities.PaymentMethods;

public interface PaymentMethod extends JpaRepository<PaymentMethods, Integer> {

}
