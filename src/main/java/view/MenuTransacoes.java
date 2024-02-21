package view;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

import models.Conta;
import models.Transferencia;
import service.ContaService;
import service.TransferenciaService;
import service.UsuarioService;


public class MenuTransacoes {
	
	
	 
	private long idContaLogada;
	private long idTransferencia;
	private long idUsuarioLogado;
	
	private TransferenciaService service;
	private ContaService contaService;
	private UsuarioService usuarioService;

	public MenuTransacoes( long idUsuarioLogado, long idContaLogada, long idTransferencia ) {
		
		this.idContaLogada = idContaLogada;
		this.idTransferencia = idTransferencia;
		this.idUsuarioLogado = idUsuarioLogado;
		
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
		Transferencia t = new Transferencia();
		
		try {
			t.setIdContaOrigem(idContaLogada);
			
			System.out.println("Digite a agência da conta destino");
			long agencia = scanner.nextLong();
			
			System.out.println("Digite o numero da conta");
			long numero = scanner.nextLong();
			t.setIdContaDestino(contaService.retornaAgenciaNum(agencia, numero).getIdConta());
			
			System.out.println("Insira o valor a ser transferido");
			BigDecimal big = new BigDecimal(scanner.nextDouble());
			
			service.transferir(t);
			System.out.println("Transferencia realizada com sucesso!");
			
		}catch (Exception e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
		
		}
		
	}


	private void listarTransferencias() {
		for (Map<String, String>m: service.retornarTransferenciasPorConta(contaService.retornaContaPorIdUsuario(idUsuarioLogado))) {
			System.out.println("Conta: "+m.get("Conta"));
			System.out.println("Tipo de movimento: "+m.get("Movimento"));
			System.out.println("Valor: "+m.get("Valor"));
			System.out.println("Tipo de transferência: "+m.get("Tipo"));
			System.out.println("Data: "+m.get("Data"));
			System.out.println("-----------------------------------------");
		}
		
	}
	
 }



	
		

	


