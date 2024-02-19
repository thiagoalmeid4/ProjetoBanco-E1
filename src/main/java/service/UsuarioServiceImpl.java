package service;

import java.time.LocalDate;

import java.time.Period;

import dao.UsuarioDao;
import models.Usuario;

public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioDao dao;

	public UsuarioServiceImpl(UsuarioDao dao) {
		this.dao = dao;
	}

	@Override
	public void salvarUsuario(Usuario usuario) {
		validarUsuario(usuario);
		dao.salvar(usuario);
	}

	@Override
	public Usuario login(String email, String senha) {
		Usuario usuario = null;

		for (Usuario u : dao.listarTodos()) {
			if (email == u.getEmail()) {
				usuario = u;
				break;
			}
		}
		if (usuario == null) {
			throw new RuntimeException("Email invalido.");
		} else if (!usuario.getSenha().equals(senha)) {
			throw new RuntimeException("Senha invalida.");
		}

		return usuario;
	}
	
	@Override
	public Usuario retornarPorId(long idUsuario) {
		return dao.retornarPorID(idUsuario);
	}

	private void validarUsuario(Usuario usuario) {

		validarDataNascimento(usuario.getDataNascimento());
		validarCpf(usuario.getCpf());
		validarEmail(usuario.getEmail());
		verificarDadosExistentes(usuario);

		if (usuario.getNome().isBlank()) {
			throw new RuntimeException("Nome deve ser informado.");
		}

		if (usuario.getSenha().length() < 6) {
			throw new RuntimeException("Senha deve conter 6 ou mais caracteres.");
		}
	}

	private void validarDataNascimento(LocalDate dataNascimento) {
		LocalDate hoje = LocalDate.now();
		Period periodo = Period.between(dataNascimento, hoje);
		int idade = periodo.getYears();

		if (idade > 90) {
			throw new RuntimeException("Data de nascimento inválida");
		}
		if (idade < 18) {
			throw new RuntimeException("O usuário deve ter 18 anos ou mais");
		}
	}

	private void validarCpf(String CPF) {
		if (CPF.equals("00000000000") ||
				CPF.equals("11111111111") ||
				CPF.equals("22222222222") || CPF.equals("33333333333") ||
				CPF.equals("44444444444") || CPF.equals("55555555555") ||
				CPF.equals("66666666666") || CPF.equals("77777777777") ||
				CPF.equals("88888888888") || CPF.equals("99999999999") ||
				(CPF.length() != 11))
			throw new RuntimeException("CPF inválido!");

		char dig10, dig11;
		int sm, i, r, num, peso;

		sm = 0;
		peso = 10;
		for (i = 0; i < 9; i++) {
			num = (int) (CPF.charAt(i) - 48);
			sm = sm + (num * peso);
			peso = peso - 1;
		}

		r = 11 - (sm % 11);
		if ((r == 10) || (r == 11))
			dig10 = '0';
		else
			dig10 = (char) (r + 48);

		sm = 0;
		peso = 11;
		for (i = 0; i < 10; i++) {
			num = (int) (CPF.charAt(i) - 48);
			sm = sm + (num * peso);
			peso = peso - 1;
		}

		r = 11 - (sm % 11);
		if ((r == 10) || (r == 11))
			dig11 = '0';
		else
			dig11 = (char) (r + 48);

		if (dig10 != CPF.charAt(9) || dig11 != CPF.charAt(10))
			throw new RuntimeException("CPF inválido!");
	}

	private void validarEmail(String email) {

		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

		if (!email.matches(regex)) {
			throw new RuntimeException("Email inválido!");
		}
	}

	private void verificarDadosExistentes(Usuario usuario) {
		for (Usuario u : dao.listarTodos()) {
			if (usuario.getEmail().equals(u.getEmail())) {
				throw new RuntimeException("Email ja cadastrado!");
			}
		}

		for (Usuario u : dao.listarTodos()) {
			if (usuario.getCpf().equals(u.getCpf())) {
				throw new RuntimeException("Cpf ja cadastrado!");
			}
		}
	}

}
