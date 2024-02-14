package service;

import java.math.BigDecimal;
import java.util.Random;

import models.Conta;
import models.Usuario;

public class ContaServiceImpl implements ContaService {

	private long proximoNumeroConta = 10000000;
	private long proximoNumeroAgencia = 1000;
	
	@Override
	public Conta gerarConta(Usuario usuario) {
		
		long numeroConta = gerarNumeroConta();
		long numeroAgencia = gerarNumeroAgencia();
		double saldoInicial = 50;
		double limiteCredito = gerarLimiteCredito(0, 150);
		
		Conta novaConta = new Conta (numeroConta, numeroAgencia, saldoInicial, limiteCredito);
		return novaConta;
	}

	@Override
	public BigDecimal retornarSaldo(Conta conta) {
		if ( conta == null) {
			throw new RuntimeException("Conta inexistente");
		}
		
		return conta.getSaldo();
		
	}
	private long gerarNumeroConta() {
		return proximoNumeroConta++;
	}
	private long gerarNumeroAgencia() {
		return proximoNumeroAgencia++;
	}
	private double gerarLimiteCredito(double min, double max) {
		
		Random limiteCredito = new Random();
		
		return limiteCredito.nextDouble();
	}

} 
