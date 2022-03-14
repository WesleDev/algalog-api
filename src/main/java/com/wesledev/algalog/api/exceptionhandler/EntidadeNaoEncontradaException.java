package com.wesledev.algalog.api.exceptionhandler;

import com.wesledev.algalog.domain.model.exception.NegocioException;

public class EntidadeNaoEncontradaException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}

}
