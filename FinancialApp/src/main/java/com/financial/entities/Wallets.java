package com.financial.entities;

import java.util.List;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
