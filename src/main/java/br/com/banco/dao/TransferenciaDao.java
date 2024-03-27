package br.com.banco.dao;

import java.util.List;

import br.com.banco.models.Transferencia;

public interface TransferenciaDao {

	void salvar(Transferencia transferencia);

	List<Transferencia> listarTodos();

	Transferencia retornarPorID(long idTransferencia);

	void transfMultipla(Transferencia transferencia);
}
