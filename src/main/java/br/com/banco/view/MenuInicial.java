package br.com.banco.view;

import java.util.Scanner;

import br.com.banco.models.Usuario;
import br.com.banco.service.ContaService;
import br.com.banco.service.TransferenciaService;
import br.com.banco.service.UsuarioService;
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
					System.out.println("╔════════════════════════════════╗");
					System.out.println("║  	     Bem-vindo             ║");
					System.out.println("╠════════════════════════════════╣");
					System.out.println("║  Menu:                         ║");
					System.out.println("║    (1) Criar novo cadastro     ║");
					System.out.println("║    (2) Fazer login             ║");
					System.out.println("║    (3) Sair do sistema         ║");
					System.out.println("╠════════════════════════════════╣");
					System.out.print("║  Digite o número da opção: ");					
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
			System.out.println("-----------------------------");
			System.out.println("|    Cadastro de Usuário    |");
			System.out.println("-----------------------------");
			
			System.out.print("Olá! Digite o seu nome:\n:");
			String nome = Input.getNome();
			
			System.out.print("Qual sua data de nascimento? (dd/mm/aaaa)\n:");
			LocalDate dataNascimento = Input.getData();
			
			System.out.println("-----------------------------");
			System.out.print("|       Informe seus dados   |\n");
			System.out.println("-----------------------------");
			
			System.out.print("Digite o seu email:\n:");
			String email = Input.getEmail();
			
			System.out.print("Qual é o seu cpf:\n:");
			String cpf = Input.getCpf();
			
			System.out.print("Crie uma senha de no mínimo 6 caracteres:\n:");
			String senha = Input.getSenha();

			Usuario usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setDataNascimento(dataNascimento);
			usuario.setEmail(email);
			usuario.setCpf(cpf);
			usuario.setSenha(senha);
			usuarioService.salvarUsuario(usuario);
			contaService.gerarConta(usuario);
			String paneSucesso = "-------------------------"+"\n| Parabéns, sua conta foi criada ;) |"+"\n-------------------------";
			System.out.println(paneSucesso);
		} catch (Exception e) {
			String paneErro = "-------------------------"+"\n|" +e.getMessage()+"! |"+"\n-------------------------";
			System.out.println(paneErro);
			registrar();
		}
	}

	public void logar(boolean repete) {
		try {
			String paneEmail = "-------------------"+"\n| Digite seu email |"+"\n-------------------";
			System.out.print(paneEmail+"\n:");
			String email = Input.getEmail();
			String paneSenha = "-------------------"+"\n| Digite sua senha |"+"\n-------------------";
			System.out.print(paneSenha+"\n:");
			String senha = Input.getSenha();

			Usuario usuarioLogado = usuarioService.login(email, senha);

			MenuUsuario menu = new MenuUsuario(usuarioLogado.getIdUsuario(),
					usuarioService, contaService, transferenciaService);
			menu.executar();
			repete = false;
		} catch (Exception e) {
			String paneErro = "-------------------------"+"\n|" +e.getMessage()+"! |"+"\n-------------------------";
			System.out.println(paneErro);
		}
	}
}