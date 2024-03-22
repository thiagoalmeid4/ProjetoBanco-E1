//package br.com.banco.dao;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import br.com.banco.dao.repository.UsuarioRepository;
//import br.com.banco.models.Usuario;
//
////@Component
//public class UsuarioDaoImpl3 implements UsuarioDao {
//
//	@Autowired
//	UsuarioRepository usuarioRepository;
//	
//	@Override
//	public void salvar(Usuario usuario) {
//		usuarioRepository.save(usuario);
//		
//	}
//
//	@Override
//	public List<Usuario> listarTodos() {
//		return usuarioRepository.findAll();
//	}
//
//	@Override
//	public Usuario retornarPorID(long idUsuario) {
//		return usuarioRepository.findById(idUsuario).get();
//	}
//
//}
