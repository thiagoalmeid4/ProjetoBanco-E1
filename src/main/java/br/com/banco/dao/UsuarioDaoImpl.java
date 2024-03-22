//package br.com.banco.dao;
//
//import java.util.List;
//import java.util.ArrayList;
//
//import br.com.banco.models.Usuario;
//
//public class UsuarioDaoImpl implements UsuarioDao {
//
//	private List<Usuario> lista = new ArrayList<>();
//
//	public UsuarioDaoImpl(List<Usuario> lista) {
//		this.lista = lista;
//	}
//
//	@Override
//	public void salvar(Usuario usuario) {
//
//		usuario.setIdUsuario(lista.size() + 1);
//		lista.add(usuario);
//
//	}
//
//	@Override
//	public List<Usuario> listarTodos() {
//
//		return lista;
//	}
//
//	@Override
//	public Usuario retornarPorID(long idUsuario) {
//
//		for (Usuario u : lista) {
//			if (u.getIdUsuario() == idUsuario) {
//				return u;
//			}
//		}
//		return null;
//	}
//
//}