package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import models.Conta;
import models.Usuario;
import service.ContaService;
import service.UsuarioService;
import service.UsuarioServiceImpl;
import java.time.LocalDate;
public class MenuInicial {
	
	private UsuarioService usuarioService;
	
	private ContaService contaService;
	
	private UsuarioDao usuarioDao;
	
	public MenuInicial (UsuarioService usuarioService) {
		
		this.usuarioService = usuarioService;
	}
	
	Scanner scanner = new Scanner(System.in);
	
	public void executar() {
			int escolha;
			boolean repete = true;
			
			while(repete) {
				boolean enquanto = true;
				while (enquanto) {
				try {
					System.out.println("(1) Criar cadastro");
					System.out.println("(2) Fazer Login");
					System.out.println("(3) Sair ");
					System.out.println("Digite o número da opção desejada: ");

					escolha = scanner.nextInt();
					scanner.nextLine();
					enquanto = false;
				} catch (Exception e) {
					System.out.println("Digite novamente somente os números listados");
					scanner.nextLine();

				}
			}

			if (escolha == 1) {
				scanner = new Scanner(System.in);
				System.out.println("Olá! Digite o seu nome:\n");
				String nome = scanner.nextLine();

				System.out.println("Qual é a sua data de nascimento?:\n");
				LocalDate dataNascimento = scanner.nextLine();

				System.out.println("Digite o seu email?:\n");
				String email = scanner.nextLine();

				System.out.println("Qual é o seu cpf?:\n");
				String cpf = scanner.nextLine();
				

				

				System.out.println("Crie uma senha de 4 a 10 caracteres:\n");
				String senha = scanner.nextLine();
		
				Usuario usuario = new Usuario();
				usuario.setNome(nome);
				usuario.setDataDeNascimento(dataNascimento);
				usuario.setEmail(email);
				usuario.setCpf(cpf);
				usuario.setSenha(senha);
				usuarioService.salvarUsuario(usuario);
				contaService.gerarConta(usuario);
					System.out.println("Usuário registrado com sucesso!");
				

			
			}else if (escolha == 2) {
				Scanner input = new Scanner(System.in);
				System.out.println("Olá! Digite o seu email:\n");
				String email = input.nextLine();

				input.nextLine();

				System.out.println("Digite sua senha:\n");
				String senha = input.nextLine();
				Usuario usuario1 = null;
				for (Usuario usuario : usuarioDao.listarTodos() ) {
					if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
						usuario1 = usuario;
						break;

					}
				}
			} else if (escolha == 3) {
				System.out.println("Saindo do programa.");
				repete = false;

	}
	}
	}
}