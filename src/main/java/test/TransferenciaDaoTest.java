package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dao.TransferenciaDao;
import dao.TransferenciaDaoImpl;
import models.Transferencia;

public class TransferenciaDaoTest {
	
	private TransferenciaDao dao;
	
	private List<Transferencia> listaTeste = new ArrayList<>();
	
	
	@Test
	public void testeSalvarTransferencia() {
		
		dao = new TransferenciaDaoImpl(listaTeste);
		var transferencia = new Transferencia();
		
		dao.salvar(transferencia);
		
		
	}
}

