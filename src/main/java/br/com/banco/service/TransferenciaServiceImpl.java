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
import br.com.banco.utils.GeradorTransferencia;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

	private TransferenciaDao dao;
	private ContaDao contaDao;
	private UsuarioDao usuarioDao;
	private GeradorTransferencia gt;

	public TransferenciaServiceImpl(TransferenciaDao dao, ContaDao contaDao, UsuarioDao usuarioDao,GeradorTransferencia gt) {
		this.dao = dao;
		this.contaDao = contaDao;
		this.usuarioDao = usuarioDao;
		this.gt = gt;
	}

	@Override
	public void transferir(Transferencia transferencia) {

		Conta contaRecebe= contaDao.retornarPorAgenciaNum(transferencia.getAgenciaDestino(), transferencia.getNumeroContaDestino());
		Conta contaPerde= contaDao.retornarPorAgenciaNum(transferencia.getAgenciaOrigem(), transferencia.getNumeroContaOrigem());
		

		
		if (transferencia.getValor() == null) {
			throw new RuntimeException("transferencia inválida");
		}

		if (transferencia.getValor().doubleValue() > contaPerde.getSaldo().doubleValue()) {
			throw new RuntimeException("Saldo insuficiente");
		}

		contaPerde.setSaldo(contaPerde.getSaldo().subtract(transferencia.getValor()));

		if (contaRecebe != null) {
			contaRecebe.setSaldo(contaRecebe.getSaldo().add(transferencia.getValor()));
		} else {
			throw new RuntimeException("ContaDestino nao encontrada");
		}

		
		transferencia.setIdContaOrigem(contaPerde.getIdConta());
		transferencia.setIdContaDestino(contaRecebe.getIdConta());
		transferencia.setData(LocalDateTime.now());
		transferencia.setTipo("TED");
		
		dao.salvar(transferencia);
		contaDao.atualizar(contaPerde);
		contaDao.atualizar(contaRecebe);
	}

	@Override
	public List<Map<String, String>> retornarTransferenciasPorConta(Conta conta) {
		List<Map<String, String>> extrato = new ArrayList<>();
		for (Transferencia t : dao.listarTodos()) {
			if ((t.getIdContaDestino() == conta.getIdConta()) || (t.getIdContaOrigem() == conta.getIdConta())) {
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

	private String retornarNomeUsuario(Conta c, Transferencia t) {
		if (c.getIdConta() == t.getIdContaDestino()) {
			return usuarioDao.retornarPorID(contaDao.retornarPorID(t.getIdContaOrigem()).getIdConta()).getNome();
		} else {
			return usuarioDao.retornarPorID(contaDao.retornarPorID(t.getIdContaDestino()).getIdConta()).getNome();
		}

	}
	
	
	public void gerarTransferencia(int i) {
		gt.geradorTransferencias(i);
	}


	public void importTransf() {
		dao.transfMultipla();
	}






}
