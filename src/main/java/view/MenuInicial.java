package view;

import java.util.Scanner;


import models.Usuario;
import service.ContaService;
import service.UsuarioService;
import java.time.LocalDate;

public class MenuInicial {

	private UsuarioService usuarioService;
	private ContaService contaService;
	
	public MenuInicial(UsuarioService usuarioService, ContaService contaService) {
		this.usuarioService = usuarioService;
		this.contaService = contaService;
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
				input = new Scanner(System.in);
				System.out.println("Olá! Digite o seu nome:\n");
				String nome = input.nextLine();

				System.out.println("Qual dia você nasceu?\n");
				int diaNascimento = input.nextInt();

				System.out.println("Qual mês você nasceu?\n");
				int mesNascimento = input.nextInt();

				System.out.println("Qual ano?\n");
				int anoNascimento = input.nextInt();

				LocalDate dataNascimento = LocalDate.of(anoNascimento, mesNascimento, diaNascimento);

				System.out.println("Digite o seu email?:\n");
				String email = input.next();

				System.out.println("Qual é o seu cpf?:\n");
				String cpf = input.next();

				System.out.println("Crie uma senha de 6 a 10 caracteres:\n");
				String senha = input.next();

				Usuario usuario = new Usuario();
				usuario.setNome(nome);
				usuario.setDataNascimento(dataNascimento);
				usuario.setEmail(email);
				usuario.setCpf(cpf);
				usuario.setSenha(senha);
				usuarioService.salvarUsuario(usuario);
				contaService.gerarConta(usuario);
				System.out.println("Usuário registrado com sucesso!");

			} else if (escolha == 2) {
				input = new Scanner(System.in);
				System.out.println("Olá! Digite o seu email:\n");
				String email = input.next();

				System.out.println("Digite sua senha:\n");
				String senha = input.next();
				
				Usuario usuarioLogado = usuarioService.login(email, senha);

				
				MenuUsuario menu = new MenuUsuario(usuarioLogado.getIdUsuario(), usuarioService, contaService);
				menu.executar();
				repete = false;
				
			} else if (escolha == 3) {
				System.out.println("Saindo do programa.");
				repete = false;

			}
		}
	}
}