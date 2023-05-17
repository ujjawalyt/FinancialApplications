package com.financial.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name ="wallets")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Wallets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="wallet_id")
	private Integer walletId;
	
	
	@Column(name="balance")
	private Double balance;
	
	
	@ManyToOne
	@JoinColumn(name ="userId")
	private Users users;
	
	@OneToMany(mappedBy = "senderWallet")
	private List<Transactions> senderWallet;
	
	@OneToMany(mappedBy = "receiverWallet")
	private List<Transactions> receiverWallet;
	
}
