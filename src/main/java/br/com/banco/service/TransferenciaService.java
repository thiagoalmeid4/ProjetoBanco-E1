package br.com.banco.service;

import java.util.List;
import java.util.Map;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;

public interface TransferenciaService {
	void transferir(Transferencia transferencia);

	List<Map<String, String>> retornarTransferenciasPorConta(Conta conta);
}
