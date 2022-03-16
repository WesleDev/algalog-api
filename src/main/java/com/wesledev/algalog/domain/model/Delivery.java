package com.wesledev.algalog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.wesledev.algalog.domain.model.exception.BusinessException;

@Entity(name = "Entrega")
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Client cliente;

	@Embedded
	private Recipient destinatario;

	private BigDecimal taxa;

	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Occurrence> ocorrencias = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	private OffsetDateTime dataPedido;

	private OffsetDateTime dataFinalizacao;

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}

	public Recipient getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Recipient destinatario) {
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

	public List<Occurrence> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Occurrence> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public Occurrence addOccorrence(String description) {
		Occurrence occorrence = new Occurrence();
		occorrence.setDescricao(description);
		occorrence.setDataRegistro(OffsetDateTime.now());
		occorrence.setEntrega(this);

		this.getOcorrencias().add(occorrence);

		return occorrence;
	}

	public void finished() {
		if (naoPodeSerFinalizada()) {
			throw new BusinessException("Entrega não pode ser finalizada");
		}
		setStatus(DeliveryStatus.FINALIZADA);
		setDataFinalizacao(OffsetDateTime.now());
	}
	
	public boolean podeSerFinalizada() {
		return DeliveryStatus.PENDENTE.equals(getStatus());
	}
	
	public boolean naoPodeSerFinalizada() {
		return !podeSerFinalizada();
	}

}
