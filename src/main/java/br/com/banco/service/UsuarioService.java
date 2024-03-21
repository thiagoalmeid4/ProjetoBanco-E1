package br.com.banco.service;

import br.com.banco.models.Usuario;

public interface UsuarioService {
	void salvarUsuario(Usuario usuario);

	Usuario login(String cpf, String senha);
	
	Usuario retornarPorId(long idUsuario);

}
