package view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

import models.Transferencia;
import service.ContaService;
import service.TransferenciaService;

public class MenuTransacoes {

	private long idContaLogada;
	private long idUsuarioLogado;

	private TransferenciaService service;
	private ContaService contaService;
	private MenuUsuario menuUsuario;

	public MenuTransacoes(long idUsuarioLogado, long idContaLogada, TransferenciaService service,
			ContaService contaService, MenuUsuario menuUsuario) {
		this.menuUsuario = menuUsuario;
		this.idContaLogada = idContaLogada;
		this.idUsuarioLogado = idUsuarioLogado;
		this.service = service;
		this.contaService = contaService;

	}

	public void executar() {
		Scanner scanner = new Scanner(System.in);
		boolean x = true;
		try {
			while (x) {

				exibirMenu();
	
				int opcao;
	
				System.out.println("Escolha uma opção:");
				opcao = scanner.nextInt();
	
				switch (opcao) {
					case 1:
						listarTransferencias();
						break;
					case 2:
						realizarTransferencia();
						break;
					case 3:
						System.out.println("Saindo");
						x = false;
						menuUsuario.executar();
					default:
						System.out.println("Opcao invalida");
				}
	
			}	
		} catch (Exception e) {
			System.out.println("Opção");
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
			BigDecimal big = new BigDecimal(Input.getValorMonetario());
			t.setValor(big.setScale(2));

			service.transferir(t);
			System.out.println("Transferencia realizada com sucesso!");

		} catch (Exception e) {
			System.out.println("Ocorreu um erro: " + e.getMessage());
		}

	}

	private void listarTransferencias() {
		try {
			for (Map<String, String> m : service
					.retornarTransferenciasPorConta(contaService.retornaContaPorIdUsuario(idUsuarioLogado))) {

				System.out.println("Pagante/receptor: " + m.get("Conta"));
				System.out.println("Tipo de movimento: " + m.get("Movimento"));
				System.out.println("Valor: " + m.get("Valor"));
				System.out.println("Tipo de transferência: " + m.get("Tipo"));
				System.out.println("Data: " + formatarData(m.get("Data")));
				System.out.println("-----------------------------------------");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private String formatarData(String data){
		LocalDateTime date = LocalDateTime.parse(data);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MM yyyy 'às' HH:mm:ss");
        return date.format(formatter);
	}

}
