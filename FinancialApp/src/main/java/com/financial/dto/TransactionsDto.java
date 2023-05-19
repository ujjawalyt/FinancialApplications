package com.financial.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import com.financial.entities.Wallets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionsDto {

	
	
    private Integer transactionId;
	private Double amount;
	private LocalDateTime timestamp;
	private String transactionType;
	private String senderName;
	private String receiverName;
	
	
}
