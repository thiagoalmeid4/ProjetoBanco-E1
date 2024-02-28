package view;

import java.time.LocalDateTime;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import service.ContaService;
import service.TransferenciaService;
import service.UsuarioService;

public class MenuUsuario {

	private long idUsuario;

	private UsuarioService usuarioService;

	private ContaService contaService;

	private TransferenciaService transferenciaService;

	public MenuUsuario(long idUsuario, UsuarioService usuarioService, ContaService contaService,
			TransferenciaService transferenciaService) {

		this.idUsuario = idUsuario;
		this.usuarioService = usuarioService;
		this.contaService = contaService;
		this.transferenciaService = transferenciaService;
	}

	public void executar() {
		try {
			Scanner input = new Scanner(System.in);
			int escolha = 0;
			boolean sair = true;

			while (sair) {
				painel();
				System.out.println("1 - Menu Transações");
				System.out.println("2 - Sair");
				escolha = input.nextInt();

				switch (escolha) {
					case 1:
						MenuTransacoes menu2 = new MenuTransacoes(idUsuario,
								contaService.retornaContaPorIdUsuario(idUsuario).getIdConta(), transferenciaService,
								contaService, this);
						menu2.executar();
						executar();
					case 2:
						MenuInicial menuInicial = new MenuInicial(usuarioService, contaService, transferenciaService);
						menuInicial.executar();
						sair = false;
						break;
					default:
						System.out.println("opção invalida!");
						break;
				}
			}
		} catch (Exception e) {
			System.out.println("opção invalida!");
			executar();
		}

	}

	private void painel() {

		String nome = usuarioService.retornarPorId(idUsuario).getNome();
		String dataAtual = retornaData();
		String agencia = contaService.retornaContaPorIdUsuario(idUsuario).getAgencia() + "";
		String numeroConta = contaService.retornaContaPorIdUsuario(idUsuario).getNumeroConta() + "";
		String saldo = contaService.retornaContaPorIdUsuario(idUsuario).getSaldo().toString();

		var modelo = "-----------------------------------------------------------------------------------------------------------"
				+ "\n| " + dataAtual + "          " + nome + "        ag * " + agencia + "       cc * " + numeroConta
				+ "              Saldo: R$ " + saldo + "        |"
				+ "\n-----------------------------------------------------------------------------------------------------------";

		System.out.println(modelo);
	}

	private String retornaData() {

		LocalDateTime agora = LocalDateTime.now();

		// Define o formato desejado
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("EEE, dd/MM/yyyy", new Locale("pt", "BR"));

		// Formata a data e hora
		String dataHoraFormatada = agora.format(formato).toUpperCase().replace(".", "");

		return dataHoraFormatada;

	}

}
