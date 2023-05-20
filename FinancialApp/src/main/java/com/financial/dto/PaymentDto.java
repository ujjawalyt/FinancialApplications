package com.financial.dto;

import javax.persistence.Column;

import com.financial.entities.PaymentMethods;
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
public class PaymentDto {

	private Integer paymentMethodId;
	private String paymentType;
	private String paymentDetails;
	private UserDto userDto;
	
	
}
