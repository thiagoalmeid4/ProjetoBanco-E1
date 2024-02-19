package service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ContaDao;
import dao.TransferenciaDao;
import dao.UsuarioDao;
import models.Conta;
import models.Transferencia;
import models.Conta;

public class TransferenciaServiceImpl implements TransferenciaService{

	private TransferenciaDao dao;
	private ContaDao contaDao;
	private UsuarioDao usuarioDao;
	
	public TransferenciaServiceImpl(TransferenciaDao dao, ContaDao contaDao, UsuarioDao usuarioDao){
		this.dao = dao;
		this.contaDao = contaDao;
		this.usuarioDao = usuarioDao;
	}
	
	@Override
	public void transferir(Transferencia transferencia) {
		
		if(transferencia.getValor() == null) {
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
		
		transferencia.setData(LocalDateTime.now());
		transferencia.setTipo("TED");
		dao.salvar(transferencia);
	}

	@Override
	public List<Map<String, String>> retornarTransferenciasPorConta(Conta conta) {
		List<Map<String,String>> extrato = new ArrayList();
		for (Transferencia t : dao.listarTodos()) {
			Map<String, String> mov = new HashMap();
			if ((t.getIdContaOrigem() == conta.getIdUsuario()) || (t.getIdContaDestino() == conta.getIdConta())) {
				if(conta.getIdConta()==t.getIdContaDestino()) {
					mov.put("Conta", usuarioDao.retornarPorID(contaDao.retornarPorID(t.getIdContaOrigem()).getIdUsuario()).getNome());
				}else {
					mov.put("Conta", usuarioDao.retornarPorID(contaDao.retornarPorID(t.getIdContaDestino()).getIdUsuario()).getNome());
				}
				mov.put("Movimento", entradaOuSaida(conta, t));
				mov.put("Valor", formataValor(conta, t));
				mov.put("Tipo", t.getTipo());
				mov.put("Data", t.getData().toString());
				
				extrato.add(mov);
			}
			
		}
		if(extrato.isEmpty()) {
			throw new RuntimeException("Conta sem registro de transferencias");
		}
		return extrato;
	}

	private String entradaOuSaida(Conta c, Transferencia t) {
		if(c.getIdConta()==t.getIdContaDestino()) {
			return "Entrada";
		}else {
			return "Saída";
		}
		
	}
	
	private String formataValor(Conta c, Transferencia t) {
		if(c.getIdConta()==t.getIdContaDestino()) {
			return ""+t.getValor();
		}else {
			return "-"+t.getValor();
		}
	}
}
