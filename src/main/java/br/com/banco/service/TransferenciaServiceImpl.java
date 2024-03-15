package br.com.banco.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.banco.dao.ContaDao;
import br.com.banco.dao.TransferenciaDao;
import br.com.banco.dao.UsuarioDao;
import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

	private TransferenciaDao dao;
	private ContaDao contaDao;
	private UsuarioDao usuarioDao;

	public TransferenciaServiceImpl(TransferenciaDao dao, ContaDao contaDao, UsuarioDao usuarioDao) {
		this.dao = dao;
		this.contaDao = contaDao;
		this.usuarioDao = usuarioDao;
	}

	@Override
	public void transferir(Transferencia transferencia) {

		if (transferencia.getValor() == null) {
			throw new RuntimeException("transferencia inválida");
		}
		Conta recebe = contaDao.retornarPorID(transferencia.getIdContaDestino());
		Conta paga = contaDao.retornarPorID(transferencia.getIdContaOrigem());

		if (transferencia.getValor().doubleValue() > paga.getSaldo().doubleValue()) {
			throw new RuntimeException("Saldo insuficiente");
		}
		for (Conta c : contaDao.listarTodos()) {
			if (c.getIdConta() == recebe.getIdConta()) {
				recebe.setSaldo(recebe.getSaldo().add(transferencia.getValor()));
				break;
			}
		}
		for (Conta c : contaDao.listarTodos()) {
			if (c.getIdConta() == paga.getIdConta()) {
				paga.setSaldo(paga.getSaldo().subtract(transferencia.getValor()));
			}
		}

		transferencia.setData(LocalDateTime.now());
		transferencia.setTipo("TED");
		dao.salvar(transferencia);
		contaDao.atualizar(paga);
		contaDao.atualizar(recebe);
	}

	@Override
	public List<Map<String, String>> retornarTransferenciasPorConta(Conta conta) {
		List<Map<String, String>> extrato = new ArrayList<>();
		for (Transferencia t : dao.listarTodos()) {
			if((t.getIdContaDestino()==conta.getIdConta())||(t.getIdContaOrigem()==conta.getIdConta())) {
				Map<String, String> mov = new HashMap<>();
			 
				mov.put("Conta", retornarNomeUsuario(conta, t));
				mov.put("Movimento", entradaOuSaida(conta, t));
				mov.put("Valor", formataValor(conta, t));
				mov.put("Tipo", t.getTipo());
				mov.put("Data", t.getData().toString());

				extrato.add(mov);
			}

		}
		if (extrato.isEmpty()) {
			throw new RuntimeException("Conta sem registro de transferencias");
		}
		return extrato;
	}

	private String entradaOuSaida(Conta c, Transferencia t) {
		if (c.getIdConta() == t.getIdContaDestino()) {
			return "Entrada";
		} else {
			return "Saída";
		}

	}

	private String formataValor(Conta c, Transferencia t) {
		if (c.getIdConta() == t.getIdContaDestino()) {
			return "" + t.getValor();
		} else {
			return "-" + t.getValor();
		}
	}
	
	private String retornarNomeUsuario (Conta c, Transferencia t) {
		if (c.getIdConta() == t.getIdContaDestino()) {
			return usuarioDao.retornarPorID(contaDao.retornarPorID(t.getIdContaOrigem()).getIdConta()).getNome();
		} else {
			return usuarioDao.retornarPorID(contaDao.retornarPorID(t.getIdContaDestino()).getIdConta()).getNome();
		}
		
	}
}
