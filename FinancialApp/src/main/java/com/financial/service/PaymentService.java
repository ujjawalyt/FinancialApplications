package com.financial.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.financial.dto.PaymentDto;
import com.financial.exception.PaymentMethodNotFoundException;
import com.financial.exception.UserNotFoundException;

@Service
public interface  PaymentService {

	 PaymentDto getPaymentMethodById(int paymentMethodId) throws PaymentMethodNotFoundException;
	    List< PaymentDto> getPaymentMethodsByUserId(int userId);
	    PaymentDto addPaymentMethod( PaymentDto paymentDto, int userId ) throws UserNotFoundException;
	    PaymentDto updatePaymentMethod(PaymentDto paymentDto, int userId)
	    		throws PaymentMethodNotFoundException;
	   String deletePaymentMethod(int paymentMethodId) throws PaymentMethodNotFoundException;
}
