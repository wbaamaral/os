package com.wbaamaral.os.services.exceptions;

public class NoResourceFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoResourceFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoResourceFoundException(String message) {
		super(message);
	}

}
