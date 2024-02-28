package view;

import java.util.Scanner;

import models.Usuario;
import service.ContaService;
import service.TransferenciaService;
import service.UsuarioService;
import java.time.LocalDate;

public class MenuInicial {

	private UsuarioService usuarioService;
	private ContaService contaService;
	private TransferenciaService transferenciaService;

	public MenuInicial(UsuarioService usuarioService, ContaService contaService,
			TransferenciaService transferenciaService) {
		this.usuarioService = usuarioService;
		this.contaService = contaService;
		this.transferenciaService = transferenciaService;
	}

	Scanner input = new Scanner(System.in);

	public void executar() {
		int escolha = 0;
		boolean repete = true;

		while (repete) {
			boolean enquanto = true;
			while (enquanto) {
				try {
					System.out.println("(1) Criar cadastro");
					System.out.println("(2) Fazer Login");
					System.out.println("(3) Sair ");
					System.out.println("Digite o número da opção desejada: ");

					escolha = input.nextInt();
					input.nextLine();
					enquanto = false;
				} catch (Exception e) {
					System.out.println("Digite novamente somente os números listados");
					input.nextLine();

				}
			}
			if (escolha == 1) {
				registrar();
			} else if (escolha == 2) {
				logar(repete);
			} else if (escolha == 3) {
				System.out.println("Saindo do programa.");
				repete = false;

			}
		}
	}

	public void registrar() {
		try {
			System.out.print("Olá! Digite o seu nome:\n");
			String nome = Input.getNome();

			System.out.print("Qual sua data de nascimento? (dd/mm/aaaa)\n");
			LocalDate dataNascimento = Input.getData();

			System.out.print("Digite o seu email?:\n");
			String email = Input.getEmail();

			System.out.print("Qual é o seu cpf?:\n");
			String cpf = Input.getCpf();

			System.out.print("Crie uma senha de no mínimo 6 caracteres:\n");
			String senha = Input.getSenha();

			Usuario usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setDataNascimento(dataNascimento);
			usuario.setEmail(email);
			usuario.setCpf(cpf);
			usuario.setSenha(senha);
			usuarioService.salvarUsuario(usuario);
			contaService.gerarConta(usuario);
			System.out.println("Usuário registrado com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			registrar();
		}
	}

	public void logar(boolean repete) {
		try {
			System.out.print("Olá! Digite o seu email:\n");
			String email = Input.getEmail();

			System.out.print("Digite sua senha:\n");
			String senha = Input.getSenha();

			Usuario usuarioLogado = usuarioService.login(email, senha);

			MenuUsuario menu = new MenuUsuario(usuarioLogado.getIdUsuario(),
					usuarioService, contaService, transferenciaService);
			menu.executar();
			repete = false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logar(repete);
		}
	}
}