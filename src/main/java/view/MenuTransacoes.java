package view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
		System.out.println("║         Menu de Transações        ║");
		System.out.println("╠═══════════════════════════════════╣");
		System.out.println("║  1 - Ver histórico de transações  ║");
		System.out.println("║  2 - Realizar transferência TED   ║");
		System.out.println("║  3 - Sair                         ║");
		System.out.println("╚═══════════════════════════════════╝");
		System.out.print("Escolha uma opção: ");

	}

	private void realizarTransferencia() {
		Scanner scanner = new Scanner(System.in);
		Transferencia t = new Transferencia();

		try {
			t.setIdContaOrigem(idContaLogada);
		
			System.out.println("╔════════════════════════════════════╗");
			System.out.println("║         Realizar Transferência     ║");
			System.out.println("╠════════════════════════════════════╣");
		
			System.out.print("║ Digite a agência da conta destino: ");
			long agencia = scanner.nextLong();
		
			System.out.print("║ Digite o número da conta destino: ");
			long numero = scanner.nextLong();
			t.setIdContaDestino(contaService.retornaAgenciaNum(agencia, numero).getIdConta());
		
			System.out.print("║ Insira o valor a ser transferido: ");
			BigDecimal valorTransferencia = new BigDecimal(Input.getValorMonetario());
			t.setValor(valorTransferencia.setScale(2));
		
			service.transferir(t);
			System.out.println("║ Transferência realizada com sucesso! ║");
			System.out.println("╚════════════════════════════════════╝");
		} catch (Exception e) {
			System.out.println("╔════════════════════════════════════╗");
			System.out.println("║   Falha ao Realizar Transferência  ║");
			System.out.println("╠════════════════════════════════════╣");
			System.out.println("║  Ocorreu um erro: " + e.getMessage());
			System.out.println("╚══════════════════════════════");
		}
	}

	private void listarTransferencias() {
		try {
			for (Map<String, String> m : service
					.retornarTransferenciasPorConta(contaService.retornaContaPorIdUsuario(idUsuarioLogado))) {
				System.out.println("================================================");
				System.out.println("Pagante/receptor: " + m.get("Conta"));
				System.out.println("Tipo de movimento: " + m.get("Movimento"));
				System.out.println("Valor: R$" + m.get("Valor"));
				System.out.println("Tipo de transferência: " + m.get("Tipo"));
				System.out.println("Data: " + formatarData(m.get("Data")));
				System.out.println("================================================");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private String formatarData(String data) {
		LocalDateTime date = LocalDateTime.parse(data);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMM yyyy 'às' HH:mm:ss",
				new Locale("pt", "BR"));
		return date.format(formatter);
	}

}
