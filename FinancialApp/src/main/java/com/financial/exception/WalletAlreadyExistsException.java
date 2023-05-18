package com.financial.exception;

public class WalletAlreadyExistsException extends Exception {

	public WalletAlreadyExistsException() {
	
	}
	
	public WalletAlreadyExistsException(String message) {
		super(message);
	}
}
