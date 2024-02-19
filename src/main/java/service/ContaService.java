package service;

import java.math.BigDecimal;

import models.Conta;
import models.Usuario;

public interface ContaService {
	Conta gerarConta(Usuario usuario);

	BigDecimal retornarSaldo(Conta conta);

}
