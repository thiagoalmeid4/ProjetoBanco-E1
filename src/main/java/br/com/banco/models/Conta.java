package br.com.banco.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idConta;
	private long idUsuario;
	private BigDecimal saldo;
	private long agencia;
	private long numeroConta;
	private BigDecimal limiteCredito;
	
	public Conta() {};

	public Conta(long idUsuario, long numeroConta, long numeroAgencia, BigDecimal saldoInicial, BigDecimal limiteCredito2) {
		
		this.idUsuario = idUsuario;
		this.numeroConta = numeroConta;
		this.agencia = numeroAgencia;
		this.saldo = saldoInicial;
		this.limiteCredito = limiteCredito2;
	}

	public long getIdConta() {
		return idConta;
	}

	public void setIdConta(long idConta) {
		this.idConta = idConta;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public long getAgencia() {
		return agencia;
	}

	public void setAgencia(long agencia) {
		this.agencia = agencia;
	}

	public long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

}
