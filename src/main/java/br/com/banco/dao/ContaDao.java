package br.com.banco.dao;

import java.util.List;

import br.com.banco.models.Conta;

public interface ContaDao {

	void salvar(Conta conta);

	List<Conta> listarTodos();

	Conta retornarPorID(long idConta);
	
	void atualizar(Conta conta);
	
	Conta retornarPorAgenciaNum(long agencia,long numero);
	
	Conta retornarPorIdUsuario(long idUsuario);
	
}
