package com.financial.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name= "bankAccounts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccounts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bank_account_id")
	private Integer bankAccountId;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="account_number")
	private String accountNumber;
	
	@Column(name="ifsc_code")
	private String ifscCode;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users users;
	
}
