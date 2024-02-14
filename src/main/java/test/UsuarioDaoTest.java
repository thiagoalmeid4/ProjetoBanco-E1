package test;

import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import models.Usuario;

public class UsuarioDaoTest {
	private UsuarioDao dao;
	private List<Usuario> lista = new ArrayList<>();

	
	public void testSalvar() {
		dao = new UsuarioDaoImpl(lista);
		Usuario u = new Usuario();
		
		dao.salvar(u);
	}
	public void testListar() {
		
	}
	public void testRetornar() {
		
	}
}
