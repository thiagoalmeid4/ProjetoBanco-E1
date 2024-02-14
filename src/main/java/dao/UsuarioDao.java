package dao;
import models.Usuario;
import java.util.List;

public interface UsuarioDao {

	void salvar (Usuario usuario);
	List<Usuario> listarTodos();
	Usuario retornarPorID(long idUsuario);
}
