package br.com.banco.service;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.stereotype.Service;

import br.com.banco.dao.ContaDao;
import br.com.banco.models.Conta;
import br.com.banco.models.Usuario;

@Service
public class ContaServiceImpl implements ContaService {

	private ContaDao dao;
	
	public ContaServiceImpl(ContaDao dao) {
		
		this.dao = dao;
	}

	@Override
	public Conta gerarConta(Usuario usuario) {
		long idUsuario = usuario.getIdUsuario();
		int numeroConta = gerarNumeroConta();
		short numeroAgencia = gerarNumeroAgencia();
		BigDecimal saldoInicial = new BigDecimal("50");
		BigDecimal limiteCredito = new BigDecimal(gerarLimiteCredito());

		Conta novaConta = new Conta(idUsuario, numeroConta, numeroAgencia, saldoInicial, limiteCredito);
		dao.salvar(novaConta);
		
		return novaConta;
	}

	@Override
	public BigDecimal retornarSaldo(Conta conta) {
		Conta c = dao.retornarPorID(conta.getIdConta());
			if(c != null) {
				return c.getSaldo();
			}
			else{
		throw new RuntimeException("Conta não encontrada!");
	}
	
	}
	
	@Override
	public Conta retornaContaPorIdUsuario(long idUsuario) {
		return dao.retornarPorIdUsuario( idUsuario);
	}

	private int gerarNumeroConta() {
		double max = 99999999;
		double min = 10000000;

			int contaAleatorio = (int) (Math.random() * (max - min + 1) + min);
		
			return contaAleatorio;
	}

	private short gerarNumeroAgencia() {
		double max = 9999;
		double min = 1000;

			short agenciaAleatorio = (short) (Math.random() * (max - min + 1) + min);
		
			return agenciaAleatorio;
	}

	private Long gerarLimiteCredito() {

		Random limiteCredito = new Random();

		long numeroAleatorio = (long) limiteCredito.nextDouble(151);

		return numeroAleatorio;
	}

	@Override
	public Conta retornaAgenciaNum(short agencia, int numero) {
		return dao.retornarPorAgenciaNum(agencia, numero);
	}

}
