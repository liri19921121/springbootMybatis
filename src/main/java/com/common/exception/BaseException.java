package com.common.exception;

public class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BaseException(String message, Exception e) {
		super(message, e);
	}

	public BaseException(String message) {
		super(message);
	}
}
