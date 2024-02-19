package service;

import java.util.List;
import java.util.Map;

import models.Conta;
import models.Transferencia;

public interface TransferenciaService {
	void transferir(Transferencia transferencia);
	List <Map<String, String>> retornarTransferenciasPorConta(Conta conta);
}
