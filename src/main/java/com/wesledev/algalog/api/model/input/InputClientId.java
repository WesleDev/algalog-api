package com.wesledev.algalog.api.model.input;

import javax.validation.constraints.NotNull;

public class InputClientId {

	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
