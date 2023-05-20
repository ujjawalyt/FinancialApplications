
package com.financial.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage("Validation Error..!");
		error.setDescription(me.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myUNFExceptionHandler(UserNotFoundException us ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(us.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(BankAccountNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myBANFExceptionHandler(BankAccountNotFoundException us ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(us.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myTNFExceptionHandler(TransactionNotFoundException ts ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(ts.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(WalletAlreadyExistsException.class)
	public ResponseEntity<MyErrorDetails> myWAEExceptionHandler(WalletAlreadyExistsException ws ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(ws.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(WalletNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myWNFExceptionHandler(WalletNotFoundException ws ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(ws.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<MyErrorDetails> myISBExceptionHandler(InsufficientBalanceException ws ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(ws.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(InvalidAmountException.class)
	public ResponseEntity<MyErrorDetails> myINVExceptionHandler(InvalidAmountException ws ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(ws.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(PaymentMethodNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myINVExceptionHandler(PaymentMethodNotFoundException ws ,WebRequest wr){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(ws.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error,HttpStatus.BAD_REQUEST);
		
	}
	
}
