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
		BigDecimal saldoInicial = new BigDecimal("50");
		BigDecimal limiteCredito = new BigDecimal(gerarLimiteCredito());

		Conta novaConta = new Conta(numeroConta, numeroAgencia, saldoInicial, limiteCredito);
		return novaConta;

	}

	@Override
	public BigDecimal retornarSaldo(Conta conta) {
		if (conta == null) {
			throw new RuntimeException("Conta inexistente");
		}

		return conta.getSaldo();

	}

	private Long gerarNumeroConta() {
		if (proximoNumeroConta >= 100000000) {

			return null;
		}
		return proximoNumeroConta++;
	}

	private Long gerarNumeroAgencia() {
		if (proximoNumeroAgencia >= 10000) {

			return null;
		}
		return proximoNumeroAgencia++;
	}

	private double gerarLimiteCredito() {

		Random limiteCredito = new Random();

		double numeroAleatorio = limiteCredito.nextDouble(151);

		return numeroAleatorio;
	}

}
