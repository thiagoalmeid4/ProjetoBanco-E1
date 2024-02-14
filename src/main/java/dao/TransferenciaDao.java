package dao;

import java.util.List;

import models.Transferencia;

public interface TransferenciaDao {

	void salvar (Transferencia transferencia);
	List<Transferencia> listarTodos();
	Transferencia retornarPorID(long idTransferencia);
}
