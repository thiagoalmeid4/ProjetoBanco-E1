package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transferencia {
	private long idTransferencia;
	private long idContaOrigem;
	private long idContaDestino;
	private BigDecimal valor;
	private LocalDateTime data;
	private String tipo;

	public long getIdTransferencia() {
		return idTransferencia;
	}

	public void setIdTransferencia(long idTransferencia) {
		this.idTransferencia = idTransferencia;
	}

	public long getIdContaOrigem() {
		return idContaOrigem;
	}

	public void setIdContaOrigem(long idContaOrigem) {
		this.idContaOrigem = idContaOrigem;
	}

	public long getIdContaDestino() {
		return idContaDestino;
	}

	public void setIdContaDestino(long idContaDestino) {
		this.idContaDestino = idContaDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
