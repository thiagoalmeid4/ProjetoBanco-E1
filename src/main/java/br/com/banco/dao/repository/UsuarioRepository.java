package br.com.banco.dao.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.banco.models.Usuario;

//@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}
