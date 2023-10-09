package com.everycommerce.product.exception;

public class InsufficientQuantitiyException extends RuntimeException{
	public InsufficientQuantitiyException(String message) {
		super(message);
	}
}
