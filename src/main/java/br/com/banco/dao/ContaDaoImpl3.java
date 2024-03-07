package br.com.banco.dao;

import java.util.List;

import br.com.banco.models.Conta;
import br.com.banco.dao.repository.ContaRepository;

public class ContaDaoImpl3 implements ContaDao{
	
	ContaRepository repository;

	@Override
	public void salvar(Conta conta) {
		repository.save(conta);
	}

	@Override
	public List<Conta> listarTodos() {
		return repository.findAll();
	}

	@Override
	public Conta retornarPorID(long idConta) {
		return repository.findById(idConta).get();
	}

	@Override
	public void atualizar(Conta conta) {
		repository.save(conta);
	}

}
