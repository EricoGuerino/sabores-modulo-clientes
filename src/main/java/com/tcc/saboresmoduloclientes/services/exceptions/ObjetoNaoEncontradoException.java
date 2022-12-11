package com.tcc.saboresmoduloclientes.services.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -3304610904359268120L;
	
	public ObjetoNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public ObjetoNaoEncontradoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
