package com.evolvice.cars.service.exceptions;

public class NoCarFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoCarFoundException(String msg) {
		super(msg);
	}

}
