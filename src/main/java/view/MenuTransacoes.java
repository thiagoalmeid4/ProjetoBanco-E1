package view;
import java.util.Scanner;
import service.ContaService;
import service.TransferenciaService;
import service.UsuarioService;


public class MenuTransacoes {
	
	
	 
	private long idContaOrigem;
	private long idContaDestino;
	private long idTransferencia;
	
	private TransferenciaService service;
	private ContaService contaService;
	private UsuarioService usuarioService;

	public MenuTransacoes(long idContaOrigem, long idContaDestino, long idTransferencia ) {
		
		this.idContaDestino = idContaDestino;
		this.idContaOrigem = idContaOrigem;
		this.idTransferencia = idTransferencia;
		
	}
		
	public void executar() {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			
			exibirMenu();
			
			int opcao;
			try {
				System.out.println("Escolha uma opção:");
				opcao = scanner.nextInt();		
			}catch(Exception e) {
				System.out.println("Opção inválida");
				scanner.nextLine();
				continue;
			}
			
			switch(opcao) {
			case 1:
				listarTransferencias();
				break;
			case 2:
				realizarTransferencia();
				break;
			case 3:
				System.out.println("Saindo");
				System.exit(0);
			default:
				System.out.println("Opcao invalida");
			}
			
		}
	}
	
	private void exibirMenu() {
		
		System.out.println("Menu de Transações");
		System.out.println("1- Listar Transferencias");
		System.out.println("2- Realizar Transferencia");
		System.out.println("3- Sair");
		
	}
	
	private void realizarTransferencia() {
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Digite o ID da conta origem: ");
			idContaOrigem = scanner.nextLong();
			scanner.nextLine();
			
			System.out.println("Digite o ID da conta destino: ");
			idContaDestino = scanner.nextLong();
			scanner.nextLine();
			
			System.out.println("Digite o valor a ser transferido: ");
			double valorTransferencia = scanner.nextDouble();
			scanner.nextLine();	
			
			System.out.println("Transferencia realizada com sucesso!");
			
		}catch (Exception e) {
			System.out.println("Ocorreu um erro" + e.getMessage());
		
		}
		
	}


	private void listarTransferencias() {
		
		
	}
	
 }



	
		

	


