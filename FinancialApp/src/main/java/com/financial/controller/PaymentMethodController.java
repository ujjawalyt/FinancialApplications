package com.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.dto.PaymentDto;
import com.financial.exception.PaymentMethodNotFoundException;
import com.financial.exception.UserNotFoundException;
import com.financial.repository.PaymentMethodRepo;
import com.financial.service.PaymentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/paymentmethod")
public class PaymentMethodController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/{paymentMethodId}")
    public ResponseEntity<PaymentDto> getPaymentMethodById(@PathVariable int paymentMethodId) 
    		throws PaymentMethodNotFoundException{
        PaymentDto paymentDto = paymentService.getPaymentMethodById(paymentMethodId);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentDto>> getPaymentMethodsByUserId(@PathVariable int userId) {
        List<PaymentDto> paymentDtos = paymentService.getPaymentMethodsByUserId(userId);
        return ResponseEntity.ok(paymentDtos);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<PaymentDto> addPaymentMethod(@RequestBody PaymentDto paymentDto, @PathVariable int userId) 
    		throws UserNotFoundException{
        PaymentDto addedPaymentDto = paymentService.addPaymentMethod(paymentDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPaymentDto);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<PaymentDto> updatePaymentMethod(@RequestBody PaymentDto paymentDto, @PathVariable int userId)
    		throws PaymentMethodNotFoundException{
        PaymentDto updatedPaymentDto = paymentService.updatePaymentMethod(paymentDto, userId);
        return ResponseEntity.ok(updatedPaymentDto);
    }

    @DeleteMapping("/{paymentMethodId}")
    public ResponseEntity<String> deletePaymentMethod(@PathVariable int paymentMethodId)
    		throws PaymentMethodNotFoundException{
        String message = paymentService.deletePaymentMethod(paymentMethodId);
        return ResponseEntity.ok(message);
    }
}
