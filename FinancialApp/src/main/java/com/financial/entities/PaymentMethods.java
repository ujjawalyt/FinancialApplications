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

@Table(name="paymentMethods")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentMethods {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="payment_method_id")
	private Integer paymentMethodId;
	
	@Column(name="payment_type")
	private String paymentType;
	
	@Column(name="payment_details")
	private String paymentDetails;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users users;
	
}
