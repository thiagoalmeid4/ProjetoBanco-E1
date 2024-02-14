package dao;

import java.util.List;

import models.Conta;

public interface ContaDao {

	void salvar (Conta conta);
	List<Conta> listarTodos();
	Conta retornarPorID(long idConta);
}
