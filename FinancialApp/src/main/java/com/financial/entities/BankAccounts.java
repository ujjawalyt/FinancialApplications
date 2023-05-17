package com.financial.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
