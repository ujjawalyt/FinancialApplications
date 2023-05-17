package com.financial.entities;

import java.util.List;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
	
	@OneToMany(mappedBy ="users")
	List<PaymentMethods> paymentMethods;	
}

