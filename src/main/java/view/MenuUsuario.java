package view;

import java.time.LocalDateTime;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import service.ContaService;
import service.UsuarioService;


public class MenuUsuario {
	
	private long idUsuario;
	
	private UsuarioService usuarioService;
	
	private ContaService contaService;
	
	public MenuUsuario(long idUsuario, UsuarioService usuarioService, ContaService contaService ) {
		
		this.idUsuario = idUsuario;
		this.usuarioService = usuarioService;
		this.contaService = contaService;
	}
	
	public void executar() {
		painel();
		
		Scanner input = new Scanner(System.in);
		int escolha = 0;
		boolean sair = true;
		
		while(sair) {
			System.out.println("1 - Menu Transações");
			System.out.println("2 - Sair");
			escolha = input.nextInt();

			switch(escolha) {
			case 1:
				//instanciar menu transação e executar
				sair = false;
				break;
			case 2:
				MenuInicial menuInicial = new MenuInicial(usuarioService, contaService);
				menuInicial.executar();
				sair = false;
				break;
			}
		}
	}
	
	private void painel() {
		
		String nome = usuarioService.retornarPorId(idUsuario).getNome();
		String dataAtual = retornaData();
		String agencia = contaService.retornaContaPorIdUsuario(idUsuario).getAgencia() + "";
		String numeroConta = contaService.retornaContaPorIdUsuario(idUsuario).getNumeroConta() + "";
		String saldo = contaService.retornaContaPorIdUsuario(idUsuario).getSaldo().toString();
		
		var modelo = "-----------------------------------------------------------------------------------------------------------"
    			+ "\n| "+ dataAtual +"          "+ nome +"        ag * "+ agencia +"       cc * "+ numeroConta +"              Saldo: R$ "+ saldo +"        |"
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
