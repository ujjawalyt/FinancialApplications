package com.financial.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financial.dto.PaymentDto;
import com.financial.dto.UserDto;
import com.financial.entities.PaymentMethods;
import com.financial.entities.Users;
import com.financial.exception.PaymentMethodNotFoundException;
import com.financial.exception.UserNotFoundException;
import com.financial.repository.PaymentMethodRepo;
import com.financial.repository.UsersRepo;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentMethodRepo paymentMethodRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UsersRepo usersRepo;
	
	
	@Override
	public PaymentDto getPaymentMethodById(int paymentMethodId) 
	throws PaymentMethodNotFoundException{
		  PaymentMethods paymentMethod = paymentMethodRepo.findById(paymentMethodId)
		            .orElse(null);

		    if (paymentMethod == null) {
		        throw new PaymentMethodNotFoundException("Payment method not found");
		    }

		    PaymentDto p=    modelMapper.map(paymentMethod, PaymentDto.class);
		    UserDto u = modelMapper.map(paymentMethod.getUsers(), UserDto.class);
		    p.setUserDto(u);
		    return p;
	}
	@Override
	public List<PaymentDto> getPaymentMethodsByUserId(int userId) {
		 List<PaymentMethods> paymentMethods = paymentMethodRepo.findByUsersUserId(userId);

		    return paymentMethods.stream()
		            .map(paymentMethod ->{
		            	PaymentDto p=   modelMapper.map(paymentMethod, PaymentDto.class);
		            	UserDto u = modelMapper.map(paymentMethod.getUsers(), UserDto.class);
		             p.setUserDto(u);
		            
		            	return p;
		            })
		            .collect(Collectors.toList());
	}
	@Override
	public PaymentDto addPaymentMethod(PaymentDto paymentDto, int userId) throws UserNotFoundException{
		
		Users user = usersRepo.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("User not found"));

	    PaymentMethods paymentMethod = modelMapper.map(paymentDto, PaymentMethods.class);
	    paymentMethod.setUsers(user);

	    PaymentMethods savedPaymentMethod = paymentMethodRepo.save(paymentMethod);

	    PaymentDto p=  modelMapper.map(savedPaymentMethod, PaymentDto.class);
	    UserDto u = modelMapper.map(paymentMethod.getUsers(), UserDto.class);
	    p.setUserDto(u);
	    return p;
	}
	@Override
	public PaymentDto updatePaymentMethod(PaymentDto paymentDto, int userId) throws PaymentMethodNotFoundException {
		
		PaymentMethods existingPaymentMethod = paymentMethodRepo.findById(paymentDto.getPaymentMethodId())
	            .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found"));

	    if (existingPaymentMethod.getUsers().getUserId() != userId) {
	        throw new  PaymentMethodNotFoundException ("You are not authorized to update this payment method");
	    }

	    modelMapper.map(paymentDto, existingPaymentMethod);

	    PaymentMethods updatedPaymentMethod = paymentMethodRepo.save(existingPaymentMethod);

	    return modelMapper.map(updatedPaymentMethod, PaymentDto.class);
	}
	@Override
	public String deletePaymentMethod(int paymentMethodId) throws PaymentMethodNotFoundException {
		
		PaymentMethods paymentMethod = paymentMethodRepo.findById(paymentMethodId)
	            .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found"));

	    paymentMethodRepo.delete(paymentMethod);

	    return "Payment method with ID " + paymentMethodId + " has been deleted.";
	}
	
}
