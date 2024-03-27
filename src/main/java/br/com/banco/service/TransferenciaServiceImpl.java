package br.com.banco.service;

import java.math.BigDecimal;
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

		if (transferencia.getValor() == null) {
			throw new RuntimeException("transferencia inválida");
		}
		Conta recebe = contaDao.retornarPorID(transferencia.getIdContaDestino());
		Conta paga = contaDao.retornarPorID(transferencia.getIdContaOrigem());

		if (transferencia.getValor().doubleValue() > paga.getSaldo().doubleValue()) {
			throw new RuntimeException("Saldo insuficiente");
		}

		var buscarContaOrigem = contaDao.retornarPorID(paga.getIdConta());

		if (buscarContaOrigem != null) {
			paga.setSaldo(paga.getSaldo().subtract(transferencia.getValor()));
		} else {
			throw new RuntimeException("ContaOrigem nao encontrada");
		}

		var buscarContaDestino = contaDao.retornarPorID(recebe.getIdConta());

		if (buscarContaDestino != null) {
			recebe.setSaldo(recebe.getSaldo().add(transferencia.getValor()));
		} else {
			throw new RuntimeException("ContaDestino nao encontrada");
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
	
	
	private void verificacaoValor() {
		var transf = new Transferencia();
		
		   if (transf.getValor().compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("O valor da transacao é necessario ser maior que 0");
		}
	}
	
	public void gerarTransferencia(int i) {
		gt.geradorTransferencias(i);
	}
}
