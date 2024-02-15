package dao;

import java.util.List;

import models.Conta;

public class ContaDaoImpl implements ContaDao{

	private List<Conta> contaFonte;
	
	public ContaDaoImpl(List<Conta> contaFonte) {
		
		this.contaFonte = contaFonte;
	}
	
	@Override
	public void salvar(Conta conta)
	{
		conta.setIdConta(contaFonte.size()+1);
		 contaFonte.add(conta);	
	}

	@Override
	public List<Conta> listarTodos() {
		
		return contaFonte;
	}

	@Override
	public Conta retornarPorID(long idConta) {
		for( Conta conta : contaFonte) {
			if (idConta == conta.getIdConta()) {
				return conta;
			}
		}
		return null;
	}
	
}
