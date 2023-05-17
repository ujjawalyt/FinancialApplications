package com.financial.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name ="users")
@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Users {

	
	@Column(name="user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	private String phone;
	
	@Column(name="password_hash")
	private String password;
	
	
	@OneToMany(mappedBy ="users")
	List<BankAccounts> bankAccounts;
	
	@OneToMany(mappedBy = "users")
	List<Wallets> wallets;
	
	@OneToMany(mappedBy ="users")
	List<PaymentMethods> paymentMethods;	
}

