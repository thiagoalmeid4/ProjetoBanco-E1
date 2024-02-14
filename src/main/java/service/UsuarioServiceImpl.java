package service;

import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDao;
import models.Usuario;

public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioDao dao;
	
	public UsuarioServiceImpl(UsuarioDao dao) {
		this.dao = dao;
	}	
	
	@Override
	public void salvarUsuario(Usuario usuario) {
		validarUsuario(usuario);
	}

	@Override
	public Usuario login(String email, String senha) {
		Usuario usuario = null;
		
		for (Usuario u : dao.listarTodos()) {
			if (email == u.getEmail()) {
				usuario = u;
				break;
			}
		}
		if(usuario == null) {
			throw new RuntimeException("Email invalido.");
		}else if(!usuario.getSenha().equals(senha)) {
			throw new RuntimeException("Senha invalida.");
		}
		
		return usuario;
	}
	
	private void validarUsuario(Usuario usuario) {
		
		if(usuario.getNome().isBlank()) {
			throw new RuntimeException("Nome deve ser informado.");
		}
		//finalizar essa verificação
		if(usuario.getCpf().length() != 11) {
			throw new RuntimeException("Cpf inválido.");
		}
		//finalizar tb
		if(usuario.getEmail().isBlank()) {
			throw new RuntimeException("Email invalido.");
		}
		if(usuario.getSenha().length() < 6) {
			throw new RuntimeException("Senha tem que ter mais que 6 dígitos.");
		}
		
		
		
	}
	
	

}
