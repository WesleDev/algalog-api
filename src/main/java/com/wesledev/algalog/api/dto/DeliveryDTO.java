package com.wesledev.algalog.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.wesledev.algalog.domain.model.DeliveryStatus;

public class DeliveryDTO {

	private Long id;
	private ResumeClienteDTO cliente;
	private RecipientDTO destinatario;
	private BigDecimal taxa;
	private DeliveryStatus status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public ResumeClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ResumeClienteDTO cliente) {
		this.cliente = cliente;
	}
	public RecipientDTO getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(RecipientDTO destinatario) {
		this.destinatario = destinatario;
	}
	public BigDecimal getTaxa() {
		return taxa;
	}
	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}
	public DeliveryStatus getStatus() {
		return status;
	}
	public void setStatus(DeliveryStatus status) {
		this.status = status;
	}
	public OffsetDateTime getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(OffsetDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
	
	
}
