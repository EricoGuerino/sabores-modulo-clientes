package com.tcc.saboresmoduloclientes.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = -3304610904359268120L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
