package service;

import models.Usuario;

public interface UsuarioService {
	void salvarUsuario (Usuario usuario);
	Usuario login(String login, String senha);
		
	

}
