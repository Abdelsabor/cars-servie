package com.evolvice.cars.service.exceptions;

public class CarAlreadyFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CarAlreadyFoundException(String msg) {
		super(msg);
	}
}