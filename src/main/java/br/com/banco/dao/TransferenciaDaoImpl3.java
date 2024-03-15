package br.com.banco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.banco.dao.repository.TransferenciaRepository;
import br.com.banco.models.Transferencia;

@Component
public class TransferenciaDaoImpl3 implements TransferenciaDao{
	
	@Autowired
	TransferenciaRepository repository;

	@Override
	public void salvar(Transferencia transferencia) {
		repository.save(transferencia);
		
	}

	@Override
	public List<Transferencia> listarTodos() {
		
		return repository.findAll();
	}

	@Override
	public Transferencia retornarPorID(long idTransferencia) {
		return repository.findById(idTransferencia).get();
	}

}
