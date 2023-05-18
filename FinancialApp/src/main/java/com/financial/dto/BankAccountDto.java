package com.financial.dto;

import javax.persistence.Column;

import com.financial.entities.BankAccounts;
import com.financial.entities.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccountDto {

	
    private Integer bankAccountId;
	private String bankName;
	private String accountNumber;
	private String ifscCode;
	private UserDto userDto;
	
}
