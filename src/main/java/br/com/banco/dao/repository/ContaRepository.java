package br.com.banco.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.banco.models.Conta;

//@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	
}
