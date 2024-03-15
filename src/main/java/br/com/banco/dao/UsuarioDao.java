package br.com.banco.dao;

import br.com.banco.models.Usuario;
import java.util.List;

public interface UsuarioDao {

	void salvar(Usuario usuario);

	List<Usuario> listarTodos();

	Usuario retornarPorID(long idUsuario);
}
