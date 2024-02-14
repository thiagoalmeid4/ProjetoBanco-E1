package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dao.ContaDao;
import dao.ContaDaoImpl;
import models.Conta;

public class ContaDaoTest {
	
	private ContaDao dao;
	
	private List<Conta> listaTeste = new ArrayList<>();
	
	
	@Test
	public void testeSalvarConta() {
		
dao = new ContaDaoImpl(listaTeste);		
		var conta = new Conta();
		
		dao.salvar(conta);
		
	}

}
