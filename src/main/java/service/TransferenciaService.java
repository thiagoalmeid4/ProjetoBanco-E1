package service;

import java.util.List;

import models.Conta;
import models.Transferencia;

public interface TransferenciaService {
	void transferir(Transferencia transferencia);
	List <Transferencia> retornarTransferenciasPorConta(Conta conta);
}
