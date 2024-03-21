package br.com.banco.service;

import java.math.BigDecimal;

import br.com.banco.models.Conta;
import br.com.banco.models.Usuario;

public interface ContaService {
	Conta gerarConta(Usuario usuario);

	BigDecimal retornarSaldo(Conta conta);
	
	Conta retornaContaPorIdUsuario(long idUsuario);

	Conta retornaAgenciaNum(short agencia, int numeroDaConta);
}
