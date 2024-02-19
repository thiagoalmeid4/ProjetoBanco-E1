package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dao.ContaDao;
import dao.TransferenciaDao;
import models.Conta;
import models.Transferencia;
import models.Conta;

public class TransferenciaServiceImpl implements TransferenciaService{

	private TransferenciaDao dao;
	private ContaDao contaDao;
	
	public TransferenciaServiceImpl(TransferenciaDao dao, ContaDao contaDao){
		this.dao = dao;
		this.contaDao = contaDao;
	}
	
	@Override
	public void transferir(Transferencia transferencia) {
		
		if(transferencia == null) {
			throw new RuntimeException("transferencia inválida");
		}
		Conta recebe = contaDao.retornarPorID(transferencia.getIdContaDestino());
		Conta paga = contaDao.retornarPorID(transferencia.getIdContaOrigem());
		
		if(transferencia.getValor().doubleValue()>paga.getSaldo().doubleValue()) {
			throw new RuntimeException("Saldo insuficiente");
		}
		for(Conta c: contaDao.listarTodos()) {
			if(c.getIdConta()==recebe.getIdConta()) {
				c.setSaldo(c.getSaldo().add(transferencia.getValor()));
				break;
			}
		}
		for(Conta c: contaDao.listarTodos()) {
			if(c.getIdConta()==paga.getIdConta()) {
				c.setSaldo(c.getSaldo().subtract(transferencia.getValor()));
			}
		}
	}

	@Override
	public List<Transferencia> retornarTransferenciasPorConta(Conta conta) {
		List<Transferencia> lista = new ArrayList();
		for (Transferencia t : dao.listarTodos()) {
			if ((t.getIdContaOrigem() == conta.getIdUsuario()) || (t.getIdContaDestino() == conta.getIdConta())) {
				lista.add(t);
			}
			if(lista.isEmpty()) {
				throw new RuntimeException("Conta sem registro de transferencias");
			}
		}
		return lista;
	}

}
