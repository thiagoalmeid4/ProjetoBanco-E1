package br.com.banco.dao;

import java.util.List;

import br.com.banco.models.Conta;

public class ContaDaoImpl implements ContaDao {

	private List<Conta> contaFonte;

	public ContaDaoImpl(List<Conta> contaFonte) {

		this.contaFonte = contaFonte;
	}

	@Override
	public void salvar(Conta conta) {
		conta.setIdConta(contaFonte.size() + 1);
		contaFonte.add(conta);
	}

	@Override
	public List<Conta> listarTodos() {

		return contaFonte;
	}

	@Override
	public Conta retornarPorID(long idConta) {
		for (Conta conta : contaFonte) {
			if (idConta == conta.getIdConta()) {
				return conta;
			}
		}
		return null;
	}

	@Override
	public void atualizar(Conta conta) {
		for(Conta c: contaFonte) {
			if(c.getIdConta()==conta.getIdConta()) {
				c=conta;
			}
		}
		
	}

}
