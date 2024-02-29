package service;

import java.math.BigDecimal;
import java.util.Random;

import dao.ContaDao;
import models.Conta;
import models.Usuario;

public class ContaServiceImpl implements ContaService {

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
		double max = 99999999;
		double min = 10000000;

			long contaAleatorio = (long) (Math.random() * (max - min + 1) + min);
		
			return contaAleatorio;
	}

	private Long gerarNumeroAgencia() {
		double max = 9999;
		double min = 1000;

			long agenciaAleatorio = (long) (Math.random() * (max - min + 1) + min);
		
			return agenciaAleatorio;
	}

	private Long gerarLimiteCredito() {

		Random limiteCredito = new Random();

		long numeroAleatorio = (long) limiteCredito.nextDouble(151);

		return numeroAleatorio;
	}

	@Override
	public Conta retornaAgenciaNum(long agencia, long numeroDaConta) {
		for(Conta c: dao.listarTodos()) {
			if ((agencia==c.getAgencia())&&(numeroDaConta==c.getNumeroConta())) {
				return c;
			}
		}
		
		throw new RuntimeException("Conta não encontrada");
	}
}
