package com.wesledev.algalog.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class InputDelivery {
	
	//se der erro falta o autowired

	@Valid
	@NotNull
	private InputClientId cliente;

	@Valid
	@NotNull
	private InputRecipient destinatario;

	@NotNull
	private BigDecimal taxa;

	public InputClientId getCliente() {
		return cliente;
	}

	public void setCliente(InputClientId cliente) {
		this.cliente = cliente;
	}

	public InputRecipient getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(InputRecipient destinatario) {
		this.destinatario = destinatario;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

}
