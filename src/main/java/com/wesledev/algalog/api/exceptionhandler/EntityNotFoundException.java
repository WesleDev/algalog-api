package com.wesledev.algalog.api.exceptionhandler;

import com.wesledev.algalog.domain.model.exception.BusinessException;

public class EntityNotFoundException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
