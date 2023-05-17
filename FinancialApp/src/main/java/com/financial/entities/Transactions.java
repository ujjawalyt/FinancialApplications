package com.financial.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="transactions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer transactionId;
	
	private Double amount;
	
	private LocalDateTime timestamp;
	
	@Column(name = "transaction_type")
    private String transactionType;
	
	@OneToOne
	@JoinColumn(name="sender_wallet_id")
	private Wallets wallets;
	
	@OneToOne
	@JoinColumn(name="receiver_wallet_id")
	private Wallets wallet;
	
}
