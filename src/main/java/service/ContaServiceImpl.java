package service;

import java.math.BigDecimal;
import java.util.Random;

import dao.ContaDao;
import models.Conta;
import models.Usuario;

public class ContaServiceImpl implements ContaService {

	private long proximoNumeroConta = 10000000;
	private long proximoNumeroAgencia = 1000;
	private ContaDao dao;
	
	public ContaServiceImpl(ContaDao dao) {
		
		this.dao = dao;
	}

	@Override
	public Conta gerarConta(Usuario usuario) {
		long idUsuario = usuario.getIdUsuario();
		long numeroConta = gerarNumeroConta();
		long numeroAgencia = gerarNumeroAgencia();
		BigDecimal saldoInicial = new BigDecimal("50");
		BigDecimal limiteCredito = new BigDecimal(gerarLimiteCredito());

		Conta novaConta = new Conta(idUsuario, numeroConta, numeroAgencia, saldoInicial, limiteCredito);
		dao.salvar(novaConta);
		
		return novaConta;
	}

	@Override
	public BigDecimal retornarSaldo(Conta conta) {
		for(Conta c : dao.listarTodos()) {
			if(c.getIdConta() == conta.getIdConta()) {
				return conta.getSaldo();
			}
		}
		return null;
	}
	
	@Override
	public Conta retornaContaPorIdUsuario(long idUsuario) {
		
		for(Conta c : dao.listarTodos()) {
			if(c.getIdUsuario() == idUsuario) {
				return c;
			}
		}
		
		return null;
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

	@Override
	public Conta retornaAgenciaNum(long agencia, long numeroDaConta) {
		for(Conta c: dao.listarTodos()) {
			if ((agencia==c.getAgencia())&&(numeroDaConta==c.getNumeroConta())) {
				return c;
			}
		}
		
		return null;
	}
}
